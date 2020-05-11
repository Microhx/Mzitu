package com.xing.mzitu.external

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.net.Api
import com.xing.mzitu.vm.BaseViewModel
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
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
class PerformNetworkTimeOutViewModel(
    private val meiziApi : Api
) : BaseViewModel() {

    fun fetchUrl(timeout: Long) {
        viewModelScope.launch {

            try{
                withTimeout(timeout) {
                    val getDataResult = meiziApi.getDataOfList()
                    Log.d("TAG","get Data Result : ${getDataResult[0].length}")
                }

            }catch (e : TimeoutCancellationException){
                e.printStackTrace()
            } catch (e : Exception) {
                e.printStackTrace()
            }
        }
    }


}