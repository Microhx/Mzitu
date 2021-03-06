package com.xing.mzitu.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.entity.MeiziItem
import com.xing.mzitu.entity.RequestLogicData
import com.xing.mzitu.entity.ResponseLogicData
import com.xing.mzitu.net.Api
import com.xing.mzitu.net.ApiClient
import com.xing.mzitu.proxy.testProxy
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import timber.log.Timber
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.*
import java.util.concurrent.TimeUnit

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/7
 *
 * version :
 *
 * desc :
 */
class MainPageViewModel : BaseViewModel() {

    private val _dataList : MutableLiveData<ResponseLogicData<MeiziItem>> = MutableLiveData()
    val dataList : LiveData<ResponseLogicData<MeiziItem>> = _dataList

    fun fetchData(data: RequestLogicData){
        viewModelScope.launch(Dispatchers.IO) {
            postStartValue()

            val response = async { testProxy() }


            val requestPageUrl = "${data.url}/page/${data.page}"
            Timber.d("request page: $requestPageUrl")

            try{
                val responseData = ApiClient.createService(Api::class.java).getMeituInfo(data.url,data.page)
                checkResponseData(responseData, data)

            }catch (e : Exception){
                e.printStackTrace()

            }
        }
    }

    override fun postSuccessDataValue(list: ArrayList<MeiziItem>, data: RequestLogicData) {
        _dataList.postValue(ResponseLogicData<MeiziItem>().apply{
            dataList = list
            pageIndex = data.page
        })
    }

    override fun postEmptyDataValue(data: RequestLogicData) {
        _dataList.postValue(ResponseLogicData<MeiziItem>().apply {
            pageIndex = data.page
            dataList  = ArrayList()
        })
    }


}