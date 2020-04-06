package com.xing.mzitu.view

import com.chad.library.adapter.base.loadmore.LoadMoreView
import com.xing.mzitu.R

/**
 * Created by Lu
 * on 2018/3/21 22:14
 */
class DefaultLoadMoreView : LoadMoreView() {

    override fun getLayoutId(): Int {
        return R.layout.view_load_more
    }

    override fun getLoadingViewId(): Int {
        return R.id.load_more_loading_view
    }

    override fun getLoadFailViewId(): Int {
        return R.id.load_more_load_fail_view
    }

    override fun getLoadEndViewId(): Int {
        return R.id.load_more_load_end_view
    }
}