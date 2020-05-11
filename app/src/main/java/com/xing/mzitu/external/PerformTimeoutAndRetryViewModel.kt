package com.xing.mzitu.external

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.net.Api
import com.xing.mzitu.vm.BaseViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.math.min

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
class PerformTimeoutAndRetryViewModel(
    private val meiziApi : Api
) : BaseViewModel(){

    fun fetchUrl() {

        viewModelScope.launch(CoroutineName("hello world")) {

        }


        viewModelScope.launch {

            val requestOne = async {

                reTryAndTimeOut(4000, 2){
                    meiziApi.getNetWorkTwoRequest()

                }
            }

            val requestTwo = async { meiziApi.getNetWorkTwoRequest() }


            val resultFeatures = listOf(requestOne,requestTwo).awaitAll()

            Log.d("TAG","get result : ${resultFeatures[0].body()}, ${resultFeatures[1].body()}")

        }
    }

    private suspend fun <T> reTryAndTimeOut(timeOut:Long,
                                            retryNumber:Int,
                                            block: suspend() -> T) : T{

        return retry(retryTime = retryNumber){
            withTimeout(timeOut) {
                block()
            }
        }
    }

    private suspend fun <T> retry(
        initDelayTime:Long = 2000,
        minDelayTime:Long = 4000,
        factor:Double     = 2.0 ,
        retryTime:Int ,
        block: suspend() -> T) : T {

            var delayTime = initDelayTime

            repeat(retryTime){

                try {
                    return block()

                }catch (e: Exception){
                    e.printStackTrace()
                }

                delay(delayTime)

                delayTime = (delayTime * factor).toLong().coerceAtLeast(minDelayTime)
            }


        return block()
    }

}