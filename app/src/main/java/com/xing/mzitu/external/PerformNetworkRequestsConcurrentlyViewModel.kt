package com.xing.mzitu.external

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.net.Api
import com.xing.mzitu.vm.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/25
 *
 * version :
 *
 * desc :
 */
class PerformNetworkRequestsConcurrentlyViewModel(
    private val meiziApi : Api
) : BaseViewModel() {

    fun fetchRequest() {

        viewModelScope.launch {

            try {

                // we need to wrap this code with a coroutineScope block
                // otherwise the app would crash on unsuccessful network requests
                coroutineScope {

                    val oneRequestNetworkDeferred = async {  meiziApi.getNetWorkOneRequest() }
                    val twoRequestNetworkDeferred = async {  meiziApi.getNetWorkTwoRequest() }

                    val oneFeatures = oneRequestNetworkDeferred.await()
                    val twoFeatures = twoRequestNetworkDeferred.await()

                    Log.d("TAG", oneFeatures.code().toString() + "..." + oneFeatures.body())
                    Log.d("TAG",twoFeatures.code().toString() + "..." + twoFeatures.body())

                }

            }catch (exception:Exception) {
                exception.printStackTrace()

            }
        }
    }



}