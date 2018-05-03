package com.daoke360.caseclass

/**
  * Created by lenovo on 2018/5/3.
  */
case class IPRule(var startIP:String,var endIP:String,var country:String,var province:String,var city:String) extends Serializable

case class RegionInfo(var country:String,var province:String,var city:String) extends Serializable
