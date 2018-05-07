package com.daoke360.common


object EventLogCountans {
  /**
    * 国家
    */
  final val LOG_COLUMN_NAME_COUNTRY = "country"
  /**
    * 省份
    */
  final val LOG_COLUMN_NAME_PROVINCE = "province"
  /**
    * 城市
    */
  final val LOG_COLUMN_NAME_CITY = "city"
  /**
    * 保存日志的表
    */
  final val HBASE_EVENT_LOG_TABLE: String = "kugou_musicc"
  /**
    * 列族
    */
  final val HBASE_EVENT_LOG_TABLE_FAMILY: String = "log"
  /**
    * 用户ip
    */
  final val LOG_COLUMN_NAME_IP: String = "ip"
  /**
    * 用户访问时间
    */
  final val LOG_COLUMN_NAME_ACCESS_TIME = "access_time"
  /**
    * 用户请求方式
    */
  final val LOG_COLUMN_NAME_REQUEST_TYPE = "request_type"
  /**
    * 用户时间名称
    */
  final val LOG_COLUMN_NAME_DATA_TYPE = "behavior"
  /**
    * behaviorData
    */
  final val LOG_COLUMN_NAME_BEHAVIOR_DATA = "behaviorData "
  /**
    * behaviorKey
    */
  final val LOG_COLUMN_NAME_BEHAVIOR_KEY = "behaviorKey"
  /**
    * zongKey
    */
  final val LOG_COLUMN_NAME_ZONG_KEY = "zongKey"
  /**
    * programId 节目
    */
  final val LOG_COLUMN_NAME_PROGRAM_ID = "programId"
  /**
    * albumId
    */
  final val LOG_COLUMN_NAME_ALBUM_ID = "albumId"
  /**
    * anchorId
    */
  final val LOG_COLUMN_NAME_ANCHOR_ID = "anchorId"
  /**
    * playTime
    */
  final val LOG_COLUMN_NAME_PLAY_TIME = "playTime"
  /**
    * on-off
    */
  final val LOG_COLUMN_NAME_ON_OFF = "on-off"
  /**
    * os_v
    */
  final val LOG_COLUMN_NAME_OS_V = "os_v"
  /**
    * modelNum
    */
  final val LOG_COLUMN_NAME_MODEL_NUM = "modelNum"
  /**
    * os_n
    */
  final val LOG_COLUMN_NAME_OS_N= "os_n"
  /**
    * ktingToken（去除）
    */
  final val LOG_COLUMN_NAME_KTING_TOKEN= "ktingToken"

}