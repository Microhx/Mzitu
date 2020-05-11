package com.xing.mzitu.external

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.net.Api
import com.xing.mzitu.vm.BaseViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/28
 *
 * version :
 *
 * desc :
 */
class PerformHandlingExceptionViewModel(
    private val api : Api
) : BaseViewModel(){

    fun fetchUrlWithTryException() {

        viewModelScope.launch {

            try {
                val responseData = api.getNetWorkTwoRequest()

            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    fun handleWithCoroutineExceptionHandler(){
        val exceptionHandler = CoroutineExceptionHandler{ _, t ->
            t.printStackTrace()
        }

        viewModelScope.launch(exceptionHandler){
            val responseData = api.getData()

            Log.d("TAG","the response data : $responseData")
        }
    }

    fun showResultsEventIfChildCoroutineFails() {
        viewModelScope.launch {

            supervisorScope {
                val oneRequestFeaturesDeferred = async { api.getNetWorkOneRequest()}
                val twoRequestFeaturesDeferred = async { api.getNetWorkTwoRequest()}

                val oneRequestFeatures = try {
                    oneRequestFeaturesDeferred.await()
                }catch (e : java.lang.Exception){
                    e.printStackTrace()
                    null
                }

                val twoRequestFeatures = try {
                    twoRequestFeaturesDeferred.await()
                }catch (e : java.lang.Exception){
                    e.printStackTrace()
                    null
                }

                val listOfData = listOf(oneRequestFeatures,twoRequestFeatures)

                Log.d("TAG","get the list of Data : $listOfData")
            }
        }
    }


}