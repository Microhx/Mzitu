package com.xing.mzitu.utils

import android.util.Log
import com.xing.mzitu.entity.MeiziDetailItem
import com.xing.mzitu.entity.MeiziItem
import org.jsoup.Jsoup
import timber.log.Timber
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

        /**
         * this is the parse the detail logic
         * response : the parse detail
         * pageIndex: the parse detail index
         * totalPage : the parse detail max page
         * needParseTotalPage : whether need to parse the total page when needed
         *
         */
        fun parseMeizituDetail(response:String?, pageIndex:Int, totalPage:Int, needParseTotalPage:Boolean) : MeiziDetailItem {
            val detailItem = MeiziDetailItem(pageIndex,totalPage,0,0,"")

            try {
                val document = Jsoup.parse(response)

                val content = document.getElementsByClass("main")?.first()?.
                              getElementsByClass("content")?.first()

                //获取本页主图片
                val aTagInfo = content?.
                                getElementsByClass("main-image")?.first()?.
                                getElementsByTag("img")?.first()

                aTagInfo?.apply {
                    detailItem.imageUrl = attr("src")
                    detailItem.imageWidth = BaseCommonUtils.safeParseInt(attr("width"))
                    detailItem.imageHeight = BaseCommonUtils.safeParseInt(attr("height"))
                }

                if(needParseTotalPage){
                    //获取本页最大数据量
                    val pageTagList = content?.getElementsByClass("pagenavi")?.first()?.getElementsByTag("a")
                    if(BaseCommonUtils.checkCollection(pageTagList)){
                        var maxPageIndex = C.FIRST_DETAIL_PAGE

                        for(m in pageTagList!!) {
                            val pageIndexValue = m.getElementsByTag("span")?.first()?.text()
                            Timber.d("get page tag index: $pageIndexValue")
                            val tempIndex = BaseCommonUtils.safeParseInt(pageIndexValue)

                            if(tempIndex > maxPageIndex){
                                maxPageIndex = tempIndex
                            }
                        }

                        Timber.d( "at last ,get the max page tag : $maxPageIndex")

                        detailItem.pageTotal = maxPageIndex

                    }else{
                        Timber.e("get page tag error: $pageTagList")
                    }
                }

            }catch (e : Exception){
                e.printStackTrace()
                detailItem.pageTotal = -1

            }

            Timber.d("the result is : $detailItem")

            return detailItem
        }
    }

}