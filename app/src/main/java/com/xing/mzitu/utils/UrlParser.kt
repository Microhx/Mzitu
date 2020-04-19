package com.xing.mzitu.utils

import android.text.TextUtils

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/19
 *
 * version :
 *
 * desc :
 */
class UrlParser {

    companion object {

        // https://www.mzitu.com/227847  --->> 227847
        // 可能存在的情况是 使用 Regex
        fun parseRelativeUrl(absoluteUrl:String) : String {
            if(TextUtils.isEmpty(absoluteUrl)) return absoluteUrl
            return absoluteUrl.replace(C.BASE_URL,"")

        }


    }
}