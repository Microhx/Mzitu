package com.xing.mzitu.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/6
 *
 * version :
 *
 * desc :
 */
abstract class BaseFragment : Fragment() {

    private var isViewPrepared = false

    private var isDataHasLoaded = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        receiveIntentOrBundles(arguments)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(getResLayoutId(), container, false)
        afterCreateView(rootView)
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        afterViewCreated()
        registerEventBus()

        isViewPrepared = true
        lazyLoad()
    }

    //this method will be call before the #onViewCreated method
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        lazyLoad()
    }

    private fun lazyLoad() {
        if(!isDataHasLoaded && isViewPrepared && userVisibleHint) {
            isDataHasLoaded = true
            onLazyLoadData()
        }
    }

    override fun onDestroyView() {
        isDataHasLoaded = false
        isViewPrepared = false
        unRegisterEventBus()
        super.onDestroyView()
    }

    override fun onDestroy() {
        isDataHasLoaded = false
        isViewPrepared = false
        super.onDestroy()
    }

    open fun registerEventBus() {}

    open fun unRegisterEventBus() {}

    open fun afterViewCreated() {}

    abstract fun getResLayoutId(): Int

    open fun afterCreateView(rootView: View){}

    open fun receiveIntentOrBundles(arguments: Bundle?){}

    open fun onLazyLoadData() {}

}