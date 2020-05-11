package com.xing.mzitu.external

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.net.Api
import com.xing.mzitu.vm.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.math.max

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
class PerformTryNetworkRequestViewModel(
    private val meziApi: Api
) : BaseViewModel(){

    fun fetchUrl() {
        viewModelScope.launch {
            val numberOfRetries = 3

            try {

                retry(numberOfRetries){
                    val resultData = meziApi.getDataOfList()
                    Log.d("TAG", "get result data: ${resultData[0].length}")
                }

            }catch (e : Exception) {
                e.printStackTrace()
            }

        }
    }

    private suspend fun <T> retry(
        times:Int,
        initDelayMillis : Long = 1000L,
        maxDelayMillis  : Long = 1000L,
        factor          : Double = 2.0,
        block: suspend() -> T
    ) : T{

        var currentDelay = initDelayMillis
        repeat(times) {
            try {
                return block()
            }catch (exception: Exception){
                exception.printStackTrace()
            }

            delay(currentDelay)

            currentDelay = (currentDelay * factor).toLong().coerceAtLeast(maxDelayMillis)
        }

        return block()
    }



}