package com.daoke360.utils
import java.util
import java.text.SimpleDateFormat
import java.util.{Locale, UUID}
import java.util.regex.Pattern

import jdk.nashorn.internal.parser.JSONParser

import scala.collection.JavaConversions.mapAsScalaMap

import scala.collection.mutable
import scala.util.parsing.json.JSONObject

/**
  * Created by lenovo on 2018/5/3.
  */
object Utils {
  /**
    * 将指定格式的日期转换成long类型的时间戳
    *
    * @param nginxTime
    *
    */
  def parseNginxTime2Long(nginxTime: String) = {
    val sdf = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss +0800", Locale.ENGLISH)
    val date = sdf.parse(nginxTime)
    date.getTime
  }

  /**
    * 验证日期是否是yyyy-MM-dd这种格式
    *
    * @param inputDate
    * @return
    */
  def validateInpuDate(inputDate: String) = {
    val reg = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$"
    val pattern = Pattern.compile(reg)
    pattern.matcher(inputDate).matches()
  }
  /**
    * 将json转化为Map
    *
    * @param json 输入json字符串
    * @return
    **/
  def json2Map(json: String): mutable.HashMap[String, Object] = {
    val map: mutable.HashMap[String, Object] = mutable.HashMap()
    val jsonParser = new JSONParser()
    //将string转化为jsonObject
    val jsonObj: JSONObject = jsonParser.parse(json).asInstanceOf[JSONObject]
    //获取所有键
    val jsonKey = jsonObj.keySet()
    val iter = jsonKey.iterator()
    while (iter.hasNext) {
      val field = iter.next()
      val value = jsonObj.get(field).toString
      if (value.startsWith("{") && value.endsWith("}")) {
        val value = mapAsScalaMap(jsonObj.get(field).asInstanceOf[util.HashMap[String, String]])
        map.put(field, value)
      } else {
        map.put(field, value)
      }
    }
    map
  }

  /**
    * 将时间戳转换成指定格式的 日期
    *
    * @param longTime
    * @param pattern
    * @return
    */
  def formatDate(longTime: Long, pattern: String) = {
    val sdf = new SimpleDateFormat(pattern)
    val calendar = sdf.getCalendar
    calendar.setTimeInMillis(longTime)
    sdf.format(calendar.getTime)
  }

  /**
    * 将指定格式的日期转换成long类型的时间戳
    *
    * @param inputDate
    * @param pattern
    */
  def parseDate2Long(inputDate: String, pattern: String) = {
    val sdf = new SimpleDateFormat(pattern)
    val date = sdf.parse(inputDate)
    date.getTime
  }

  /**
    * 生成随机的机器数字    1:1c5caad3-63f7-4e31-abee-1f0bcb188f91
    */
  def getUUID()={
    var  uuid=UUID.randomUUID()
    var str = uuid.toString
    var uuidStr=str.replace("-", "")
    uuidStr
  }
}

