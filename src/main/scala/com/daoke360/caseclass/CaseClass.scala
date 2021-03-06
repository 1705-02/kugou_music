package com.daoke360.caseclass

import com.daoke360.common.GlobalContants




case class IPRule(var startIP: Long, var endIP: Long, var country: String, var province: String, var city: String) extends Serializable
case class RegionInfo(var country: String=GlobalContants.DEFALUT_VALUE, var province: String=GlobalContants.DEFALUT_VALUE, var city: String=GlobalContants.DEFALUT_VALUE) extends Serializable
