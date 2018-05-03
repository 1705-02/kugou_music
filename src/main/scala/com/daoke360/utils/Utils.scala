package com.daoke360.utils

import java.text.SimpleDateFormat

/**
  * Created by lenovo on 2018/5/3.
  */
object Utils {

  /**
    * 将指定格式的日期转换成long类型的时间戳
    */

  def parseDate2Long(inputDate: String, pattern: String) ={
    val sdf = new SimpleDateFormat(pattern)
    val date = sdf.parse(inputDate)
    date.getTime
  }

}
