package com.xing.mzitu.entity

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/15
 *
 * version :
 *
 * desc :
 */

@Parcelize
data class MeiziDetailItem(var pageIndex:Int = 1,
                           var pageTotal:Int ,
                           var imageWidth:Int = 0,
                           var imageHeight:Int = 0,
                           var imageUrl:String) : Parcelable {

    override fun toString(): String {
        return "MeiziDetailItem(pageIndex=$pageIndex, pageTotal=$pageTotal, " +
                "imageWidth=$imageWidth, imageHeight=$imageHeight, imageUrl='$imageUrl')"
    }


}