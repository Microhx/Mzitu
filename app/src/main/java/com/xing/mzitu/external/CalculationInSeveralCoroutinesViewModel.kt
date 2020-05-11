package com.xing.mzitu.external

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.vm.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigInteger
import kotlin.system.measureTimeMillis

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
class CalculationInSeveralCoroutinesViewModel(
    private val factorialCalculator : FactorialCalculator = FactorialCalculator(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.Default

) : BaseViewModel()  {

    fun performCalculate(
        factorial:Int,
        numberOfCoroutines:Int,
        yieldDuringCalculation:Boolean
    ) {

        viewModelScope.launch {

            var factorialData = BigInteger.ONE
            val calculateTime = measureTimeMillis {
                factorialData = factorialCalculator.calculateFactorial(factorial,
                    numberOfCoroutines,
                    dispatcher,
                    yieldDuringCalculation)
            }

            Log.d("TAG","get the factorialData : $factorialData, it cost time: $calculateTime")
        }
    }


}