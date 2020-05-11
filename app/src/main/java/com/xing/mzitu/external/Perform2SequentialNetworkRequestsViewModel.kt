package com.xing.mzitu.external

import androidx.lifecycle.viewModelScope
import com.xing.mzitu.net.Api
import com.xing.mzitu.vm.BaseViewModel
import kotlinx.coroutines.launch

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/25
 *
 * version :
 *
 * desc : 串行执行两个网络请求
 */
class Perform2SequentialNetworkRequestsViewModel(
    private val meiziApi : Api
) : BaseViewModel() {

    fun fetchRequest() {
        viewModelScope.launch {
            val netWorkOneRequest = meiziApi.getNetWorkOneRequest()
            val netWorkTwoRequest = meiziApi.getNetWorkTwoRequest()
        }
    }
}