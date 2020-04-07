package com.xing.mzitu.adapter

import android.widget.ImageView
import coil.api.load
import com.chad.library.adapter.base.BaseViewHolder
import com.xing.mzitu.R
import com.xing.mzitu.entity.MeiziItem
import com.xing.mzitu.utils.C

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
class MainPageAdapter : BaseCommonAdapter<MeiziItem>(R.layout.main_page_item_layout) {

    override fun convert(holder: BaseViewHolder, item: MeiziItem?) {

        holder.getView<ImageView>(R.id.id_item_image_view).load(item?.image_url){
            addHeader(C.ORIGIN_HEADER, C.ORIGIN_VALUE)
            addHeader(C.REFERER_HEADER, C.REFERER_VALUE)
            addHeader(C.USER_AGENT_HEADER, C.USER_AGENT_VALUE)
        }

        holder.setText(R.id.id_item_tv_desc, item?.image_desc)
    }


}