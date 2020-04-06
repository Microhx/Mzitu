package com.xing.mzitu.entity

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/5
 *
 * version :
 *
 * desc : 妹子列表分享基础类
 */
data class MeiziItem(
    var content_url: String? = "",
    var image_url : String? = "",
    var image_desc: String? = "",
    var image_width: String? = "",
    var image_height: String? = "",
    var item_time : String? = ""
)