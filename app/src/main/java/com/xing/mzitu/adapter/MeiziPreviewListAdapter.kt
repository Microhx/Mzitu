package com.xing.mzitu.adapter

import android.widget.ImageView
import coil.api.load
import com.chad.library.adapter.base.BaseViewHolder
import com.xing.mzitu.R
import com.xing.mzitu.entity.MeiziDetailItem
import com.xing.mzitu.utils.C

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/18
 *
 * version :
 *
 * desc :
 */
class MeiziPreviewListAdapter : BaseCommonAdapter<MeiziDetailItem>(R.layout.preview_item_layout){

    override fun convert(holder: BaseViewHolder, item: MeiziDetailItem?) {
        holder.setText(R.id.id_tv_count, "${item?.pageIndex}")

        holder.getView<ImageView>(R.id.id_preview_image).load(item?.imageUrl){
            addHeader(C.ORIGIN_HEADER, C.ORIGIN_VALUE)
            addHeader(C.REFERER_HEADER, C.REFERER_VALUE)
            addHeader(C.USER_AGENT_HEADER, C.USER_AGENT_VALUE)
        }
    }

}


