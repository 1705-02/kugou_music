package com.daoke360.task.analysislog.utils



import com.daoke360.caseclass.{IPRule, RegionInfo}

import scala.util.control.Breaks._
/**
  * Created by tian on 2018/5/6.
  */
object IpAnalysisUtils {
  /**
    * 将对应ip的地理信息查找出来
    * @param outIp 十进制ip
    * @param ipRules ip规则库
    */
  def binnerySearch(outIp: Long, ipRules: Array[IPRule]) = {
    var min = 0
    var max =ipRules.length-1
    var index = -1
    breakable({
      while (min <= max) {
        var middle = (min + max) / 2
        val ipRule = ipRules(middle)
        if (outIp >= ipRule.startIP && outIp <= ipRule.endIP) {
          index = middle
          break()
        } else if (outIp < ipRule.startIP) {
          max = middle - 1
        } else if (outIp > ipRule.endIP) {
          min = middle + 1
        }
      }
    })
    index
  }

  def analysisIp(inputIp:String, ipRules:Array[IPRule])={
    var infor = RegionInfo()
    var outIp = ip2Long(inputIp)
    val index = binnerySearch(outIp, ipRules)
    if (index != -1) {
      val iPRule = ipRules(index)
      infor.country = iPRule.country
      infor.province = iPRule.province
      infor.city = iPRule.city
    }
    infor
  }

  /**
    * 将ip装换成十进制
    * @param inputIp 输入ip 如：192.168.134.211
    * @return 返回十进制  122666959666
    */
  def ip2Long(inputIp:String):Long={
    var outIp:Long=0
    var fileds = inputIp.split("[.]")
    for (i <- 0 until(fileds.length)){
      outIp=outIp << 8 | fileds(i).toLong
    }
    outIp
  }
}
ss
