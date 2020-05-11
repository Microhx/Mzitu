package com.xing.mzitu.external

import kotlinx.coroutines.*
import java.math.BigInteger

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
class FactorialCalculator {

    suspend fun calculateFactorial(
        factorialOf :Int ,
        numberOfThreads:Int,
        dispatcher: CoroutineDispatcher,
        //执行过程中关闭计算
        yieldDuringCalculation:Boolean

    ): BigInteger{
        val subRanges = createSubRangeList(factorialOf,numberOfThreads,dispatcher)

        return withContext(dispatcher) {

            subRanges.map {subRange ->
                async {
                    calculateFactorialSubRange(subRange,yieldDuringCalculation)
                }
            }.awaitAll().fold(BigInteger.ONE, { acc, element ->
                    if(yieldDuringCalculation){
                        yield()
                    }
                    acc.multiply(element)
            })
        }
    }

    private suspend fun calculateFactorialSubRange(
        subRange: SubRange,
        yieldDuringCalculation: Boolean): BigInteger {
        
        var factorial = BigInteger.ONE
        for (i in subRange.start..subRange.end) {
            if (yieldDuringCalculation) {
                yield()
            }

            factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
        }

        return factorial
    }

    private suspend fun createSubRangeList(
        factorial:Int,
        numberOfSubRanges:Int,
        dispatcher: CoroutineDispatcher) :
        List<SubRange>  =

        withContext(dispatcher) {
            val quotient = factorial.div(numberOfSubRanges)
            val rangesList = mutableListOf<SubRange>()

            var curStartIndex = 1

            repeat(numberOfSubRanges - 1) {
                rangesList.add(
                    SubRange(
                        curStartIndex ,
                        curStartIndex + (quotient - 1)

                    )
                )

                curStartIndex += quotient
            }


            rangesList.add(SubRange(curStartIndex, factorial))

            rangesList
        }






    data class SubRange(val start:Int, val end:Int)



}