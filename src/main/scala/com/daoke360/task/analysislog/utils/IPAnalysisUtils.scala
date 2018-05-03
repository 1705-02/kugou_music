package com.daoke360.task.analysislog.utils

import com.daoke360.caseclass.IPRule
import com.daoke360.common.{EventLogContants, GlobalContants}
import org.apache.commons.lang.StringUtils
import scala.collection.mutable
/**
  * Created by lenovo on 2018/5/3.
  */
object IPAnalysisUtils {

  def analysisLog(logText:String,ipRules:Array[IPRule]) = {
    var eventLogMap:mutable.HashMap[String,String] = null
    if(StringUtils.isNotBlank(logText)){
      val field = logText.split(GlobalContants.LOG_SPLIT_FLAG)
      if(field.length == 8){
        eventLogMap = new mutable.HashMap[String,String]()
        eventLogMap.put(EventLogContants.LOG_COLUMN_NAME_IP,field(0))
        eventLogMap.put(EventLogContants.LOG_COLUMN_NAME_ACCESS_TIME,field(4))

      }
    }
  }
}
