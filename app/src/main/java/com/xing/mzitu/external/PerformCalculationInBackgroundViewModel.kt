package com.xing.mzitu.external

import androidx.lifecycle.ServiceLifecycleDispatcher
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.vm.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception
import java.math.BigInteger
import kotlin.system.measureTimeMillis

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
class PerformCalculationInBackgroundViewModel(
    private val defaultDispatcher : CoroutineDispatcher = Dispatchers.Default
) : BaseViewModel() {

    fun fetchLogic() {
        viewModelScope.launch {
            try {

                var resultData = BigInteger("0")
                val measureData = measureTimeMillis {
                    resultData = calculateFactorialOf(1000)
                }

                val measureDataString = integerToString(resultData)

            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }

    private suspend fun calculateFactorialOf(number:Int): BigInteger {
        var result = 1
        withContext(defaultDispatcher) {
            for(m in 1..number){
                result *= m
            }
        }
        return result.toBigInteger()
    }

    private suspend fun integerToString(number:BigInteger){
        withContext(defaultDispatcher) {
             number.toString()
        }
    }
}