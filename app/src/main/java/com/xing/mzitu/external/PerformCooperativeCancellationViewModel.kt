package com.xing.mzitu.external

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.vm.BaseViewModel
import kotlinx.coroutines.*
import java.math.BigInteger
import kotlin.system.measureTimeMillis

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/26
 *
 * version :
 *
 * desc : 终止任务
 */
class PerformCooperativeCancellationViewModel(
    private val dispatcher : CoroutineDispatcher = Dispatchers.Default
): BaseViewModel() {

    private var calculationJob : Job?= null

    fun fetchUrl() {
        calculationJob = viewModelScope.launch {

            try {

                var dataResult = BigInteger.ZERO
                measureTimeMillis {
                    dataResult = getBigData(200)
                }

                Log.d("TAG","get data result : $dataResult")

            }catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }

    //cancel the task
    fun cancelAllTask() {
        calculationJob?.cancel()
    }


    private suspend fun getBigData(maxValue:Int) : BigInteger {
        var factorial = BigInteger.ONE
        withContext(dispatcher){
            for(m in 1..maxValue){

                // yield enables cooperative cancellations
                // alternatives:
                // - ensureActive()
                // - isActive() - possible to do clean up tasks with
                yield()

                factorial = factorial.multiply(BigInteger.valueOf(m.toLong()))
            }
        }

        return factorial
    }

}