package com.xing.mzitu.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.entity.MeiziItem
import com.xing.mzitu.entity.RequestLogicData
import com.xing.mzitu.entity.ResponseLogicData
import com.xing.mzitu.net.Api
import com.xing.mzitu.net.ApiClient
import com.xing.mzitu.utils.HtmlParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.ArrayList

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

            Log.e("TAG", "=======${data.url}=======>>${data.page}")

            val requestPageUrl = "${data.url}/page/${data.page}"
            Log.d("TAG","request page: $requestPageUrl")

            val responseData = ApiClient.createService(Api::class.java).getMeituInfo(data.url,data.page)

            checkResponseData(responseData, data)

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