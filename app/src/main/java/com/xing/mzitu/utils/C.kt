package com.xing.mzitu.utils

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/5
 *
 * version :
 *
 * desc :
 */
class C  {

    companion object {

        /**妹子图基础*/
        const val BASE_URL = "https://www.mzitu.com/"

        //Network Requests Header
        const val USER_AGENT = "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36"
        const val ORIGIN = "origin: https://www.mzitu.com"
        const val REFERER = "referer: https://www.mzitu.com"

        //Image Header
        const val USER_AGENT_HEADER = "user-agent"
        const val USER_AGENT_VALUE  = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36"

        const val ORIGIN_HEADER     = "origin"
        const val ORIGIN_VALUE      = "https://www.mzitu.com"

        const val REFERER_HEADER    = "referer"
        const val REFERER_VALUE     = "https://www.mzitu.com"

        //-------------------首页------------------------------
        //最热
        const val MAIN_PAGE_HOT     = "hot"
        //推荐
        const val MAIN_PAGE_RECOMMEND = "best"
        //专题
        const val MAIN_PAGE_SUBJECT   = "zhuanti"

        //--------------------分类-----------------------------
        const val CATEGORY_PAGE_SEXY = "xinggan"

        const val CATEGORY_PAGE_JAPAN = "japan"

        const val CATEGORY_PAGE_TAIWAN = "taiwan"

        const val CATEGORY_PAGE_ZIPAI  = "zipai"

        const val CATEGORY_PAGE_JIEPAI = "jiepai"

        const val CATEGORY_PAGE_DAILY  = "all"

    }

}