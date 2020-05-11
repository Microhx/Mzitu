package com.xing.mzitu.external

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.net.Api
import com.xing.mzitu.vm.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.awaitAll
import okhttp3.internal.wait

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
class PerformVariableAmountOfNetworkRequestsViewModel(
    private val meiziApi: Api
) : BaseViewModel() {


    /**
     * 串行执行可变任务
     */
    fun fetchRequest() {
        viewModelScope.launch {

            try {

                val dataListFeatures = meiziApi.getDataOfList().map {
                    data -> meiziApi.getMeituDetail(data, 1)
                }

                Log.d("TAG", "getResult : ${dataListFeatures[0].body()}")

            }catch (e : Exception) {
                e.printStackTrace()
            }

        }
    }

    /**
     * 并行执行可变任务，并且等待所有任务执行完成 并返回
     */
    fun fetchRequestAsync() {

        viewModelScope.launch {

            try {
                val dataOfList = meiziApi.getDataOfList()

                val resultMap = dataOfList.map { data ->
                        {
                            async {
                                meiziApi.getMeituDetail(data, 1)
                            }
                        }
                }

            }catch (e : Exception){
                e.printStackTrace()
            }
        }
    }


}