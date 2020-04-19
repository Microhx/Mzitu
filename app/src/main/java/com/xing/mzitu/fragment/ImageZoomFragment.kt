package com.xing.mzitu.fragment

import android.os.Bundle
import coil.api.load
import com.xing.mzitu.R
import com.xing.mzitu.base.BaseFragment
import com.xing.mzitu.entity.MeiziDetailItem
import com.xing.mzitu.utils.C
import kotlinx.android.synthetic.main.fragment_image_zoom_layout.*

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/19
 *
 * version :
 *
 * desc : 图片大图缩放浏览
 */
class ImageZoomFragment : BaseFragment() {

    private var mMeiziDetailItem : MeiziDetailItem?= null

    override fun receiveIntentOrBundles(arguments: Bundle?) {
        arguments?.apply {
            mMeiziDetailItem = getParcelable("detailItem")
        }
    }

    override fun getResLayoutId(): Int = R.layout.fragment_image_zoom_layout

    override fun onLazyLoadData() {
        mMeiziDetailItem?.apply {
            id_photo_view.load(imageUrl) {

                addHeader(C.ORIGIN_HEADER, C.ORIGIN_VALUE)
                addHeader(C.REFERER_HEADER, C.REFERER_VALUE)
                addHeader(C.USER_AGENT_HEADER, C.USER_AGENT_VALUE)

                crossfade(true)
            }
        }
    }

    companion object {
        fun getInstance(item : MeiziDetailItem?= null) : ImageZoomFragment{
            return ImageZoomFragment().apply {
                arguments = Bundle().apply {
                    putParcelable("detailItem",item)
                }
            }
        }
    }

}
