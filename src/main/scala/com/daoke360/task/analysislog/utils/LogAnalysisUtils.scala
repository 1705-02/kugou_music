package com.daoke360.task.analysislog.utils

import java.util.Base64

import com.daoke360.caseclass.IPRule
import com.daoke360.common.{EventLogCountans, GlobalCountans}
import com.daoke360.utils.Utils
import org.apache.commons.lang.StringUtils

import scala.collection.mutable


/**
  * Created by tian on 2018/5/6.
  */
object LogAnalysisUtils {
  /**
    * 处理请求参数
    * @param requestBody  请求
    * @param eventLogMap
    *GET /?bData=eyJrdGluZ1Rva2VuIjoidXNVTjkyQ1lCR1NwT2JrY28rd2c3TjB4RXlkMnllQXF5dlwvV3ZYQ0ozSTNzS2ljUm9kbWJuUzJBeDRaSHFXVVhodnFmdUphbmlucWxyR3I4NlNIcWZRPT0iLCJiZWhhdmlvcktleSI6IkRGU0oxMDEiLCJiZWhhdmlvckRhdGEiOnsiem9uZ0tleSI6IkZNMTAwIn19 HTTP/1.1|200|5|"Dalvik/2.1.0 (Linux; U; Android 8.0.0; HWI-AL00 Build/HUAWEIHWI-AL00)" sendfileon
    */
  def handlerRequestBody(requestBody: String, eventLogMap: mutable.HashMap[String, String]) = {
    val fields = requestBody.split("\\/")
    if (fields.length == 3) {
      //用户请求方式
      eventLogMap.put(EventLogCountans.LOG_COLUMN_NAME_REQUEST_TYPE, fields(0))
      val requestParams = fields(1).split(" ")
      if (requestParams.length == 2) {
        var dataParams = requestParams(0).split("\\=")
        var datatype = dataParams(0).split("\\?")
        if (datatype.length == 2 ) {
          eventLogMap.put(EventLogCountans.LOG_COLUMN_NAME_DATA_TYPE, datatype(1))
        }
        if (dataParams.length == 2) {
          var asBytes = Base64.getDecoder().decode(dataParams(1))
          var data = new String(asBytes, "utf-8")
          if(data!=null){
            var map = Utils.json2Map(data)
            if (map.size > 0) {
              map.foreach(line => {
                if (!line._1.equals(EventLogCountans.LOG_COLUMN_NAME_KTING_TOKEN)) {
                  if (line._1.equals(EventLogCountans.LOG_COLUMN_NAME_BEHAVIOR_KEY) && line._2 != null) {
                    eventLogMap.put(line._1, line._2.toString)
                  } else if (line._1.equals(EventLogCountans.LOG_COLUMN_NAME_BEHAVIOR_DATA) && line._2 != null) {
                    eventLogMap.put(line._1, line._2.toString)
                  }
                }
              })
            }
          }
        }
      }
    }
  }

  /**
    * 处理ip，解析成地域信息
    *
    * @param eventLogMap
    * @return
    */
  def handlerIP(eventLogMap: mutable.HashMap[String, String], ipRules: Array[IPRule]) = {
    val ip = eventLogMap(EventLogCountans.LOG_COLUMN_NAME_IP)
    val regionInfo = IpAnalysisUtils.analysisIp(ip, ipRules)
    eventLogMap.put(EventLogCountans.LOG_COLUMN_NAME_COUNTRY, regionInfo.country)
    eventLogMap.put(EventLogCountans.LOG_COLUMN_NAME_PROVINCE, regionInfo.province)
    eventLogMap.put(EventLogCountans.LOG_COLUMN_NAME_CITY, regionInfo.city)
  }

  def handlerHttpBody(httpBody: String, eventLogMap: mutable.HashMap[String, String]) = {
    val fields = httpBody.split("\\/")
    if (fields.length == 3) {
      var version = fields(1).split("\\;")
      var system = fields(2).split("\\)")
      eventLogMap.put(EventLogCountans.LOG_COLUMN_NAME_MODEL_NUM, system(0))
      if (version.length == 4) {
        var os = version(2).split(" ")
        eventLogMap.put(EventLogCountans.LOG_COLUMN_NAME_OS_V, os(2))
        eventLogMap.put(EventLogCountans.LOG_COLUMN_NAME_OS_N, os(1))
      }
    }
  }

  /**
    *
    * @param logTxt
    * @param ipRules
    * 183.227.7.117|0.000|-|30/Mar/2018:03:23:09 +0800|GET /?bData=eyJrdGluZ1Rva2VuIjoidXNVTjkyQ1lCR1NwT2JrY28rd2c3TjB4RXlkMnllQXF5dlwvV3ZYQ0ozSTNzS2ljUm9kbWJuUzJBeDRaSHFXVVhodnFmdUphbmlucWxyR3I4NlNIcWZRPT0iLCJiZWhhdmlvcktleSI6IkRGU0oxMDEiLCJiZWhhdmlvckRhdGEiOnsiem9uZ0tleSI6IkZNMTAwIn19 HTTP/1.1|200|5|"Dalvik/2.1.0 (Linux; U; Android 8.0.0; HWI-AL00 Build/HUAWEIHWI-AL00)" sendfileon
    */
  def analysisLog(logTxt: String, ipRules: Array[IPRule]) = {
    var eventLogMap: mutable.HashMap[String, String] = null
    if (StringUtils.isNotBlank(logTxt)) {
      if(logTxt.contains(GlobalCountans.DEL_FLAG)){
        val field = logTxt.split(GlobalCountans.LOG_SPLIT_FLAG)
        if (field.length == 8) {
          eventLogMap = new mutable.HashMap[String, String]()
          //用户ip
          eventLogMap.put(EventLogCountans.LOG_COLUMN_NAME_IP, field(0))
          //用户访问时间
          var access_time = Utils.parseNginxTime2Long(field(3)).toString
          eventLogMap.put(EventLogCountans.LOG_COLUMN_NAME_ACCESS_TIME, access_time)
          val requestBody = field(4)
          //处理请求参数
          handlerRequestBody(requestBody, eventLogMap)
          val httpBody = field(7)
          //处理http体
          handlerHttpBody(httpBody, eventLogMap)
          //处理ip
          handlerIP(eventLogMap, ipRules)
        }
      }
    }

    eventLogMap
  }
}


