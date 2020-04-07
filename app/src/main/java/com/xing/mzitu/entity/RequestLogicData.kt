package com.xing.mzitu.entity

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/7
 *
 * version :
 *
 * desc :
 */
data class RequestLogicData(

    //当前的page size
    var page : Int = 1,

    //当前url
    var url: String? = ""
)