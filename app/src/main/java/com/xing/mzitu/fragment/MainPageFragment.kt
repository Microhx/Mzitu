package com.xing.mzitu.fragment

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.xing.mzitu.adapter.BaseCommonAdapter
import com.xing.mzitu.adapter.MainPageAdapter
import com.xing.mzitu.base.BaseRefreshFragment
import com.xing.mzitu.entity.MeiziItem
import com.xing.mzitu.entity.RequestLogicData
import com.xing.mzitu.entity.ResponseLogicData
import com.xing.mzitu.logic.LoadingState
import com.xing.mzitu.vm.DataViewModel
import com.xing.mzitu.vm.MainPageViewModel

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/6
 *
 * version :
 *
 * desc : 首页
 */
class MainPageFragment : BaseRefreshFragment<MeiziItem>() {

    private var requestUrl:String? = null

    override fun receiveIntentOrBundles(arguments: Bundle?) {
        arguments?.apply {
            requestUrl = getString("requestUrl")
        }
    }

    private lateinit var mMainDataViewModel: MainPageViewModel

    override fun onLazyLoadData() {
        mMainDataViewModel = ViewModelProvider(this@MainPageFragment).get(MainPageViewModel::class.java)
        mMainDataViewModel.loading.observe(this,
            Observer<LoadingState> { t -> Log.d("TAG", "loading changed: $t") })

        mMainDataViewModel.dataList.observe(this, Observer<ResponseLogicData<MeiziItem>> { t -> parseData(t) })

        onRefresh()
    }

    override fun subClassInitAdapter(): BaseCommonAdapter<MeiziItem> = MainPageAdapter()

    override fun requestLoadData(currentPage: Int) {
        Log.d("TAG","-----request load data-------->$currentPage")

        mMainDataViewModel.fetchData(RequestLogicData(currentPage, requestUrl))
    }

    companion object {

        fun getInstance(requestUrl:String) : MainPageFragment{
            return MainPageFragment().apply {
                arguments = Bundle().apply {
                    putString("requestUrl", requestUrl)
                }
            }
        }
    }


}