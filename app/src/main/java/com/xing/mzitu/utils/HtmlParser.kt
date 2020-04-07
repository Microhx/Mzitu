package com.xing.mzitu.utils

import com.xing.mzitu.entity.MeiziItem
import org.jsoup.Jsoup
import java.lang.Exception

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
class HtmlParser {


    companion object {

        /**
         * 解析 Html 数据
         */
        fun parseMeizituList(response:String, type:Int) : ArrayList<MeiziItem> {
            val resultDataList = ArrayList<MeiziItem>()

            try {
                val document = Jsoup.parse(response)
                val liListData = document.getElementsByClass("main")?.get(0)?.
                                          getElementsByClass("main-content")?.get(0)?.
                                          getElementsByClass("postlist")?.get(0)?.
                                          getElementsByTag("li")

                liListData?.forEach {item ->
                    val itemEntity = MeiziItem()

                    itemEntity.content_url = item.getElementsByTag("a")?.first()?.attr("href")
                    item.getElementsByClass("lazy")?.first()?.apply {
                        itemEntity.image_url = attr("data-original")
                        itemEntity.image_desc = attr("alt")
                        itemEntity.image_width  = attr("width")
                        itemEntity.image_height = attr("height")
                    }

                    itemEntity.item_time = item.getElementsByClass("time")?.first()?.text()

                    resultDataList.add(itemEntity)
                }

            }catch (e: Exception){
                e.printStackTrace()
            }

            return resultDataList
        }

    }

}