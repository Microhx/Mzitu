package com.xing.mzitu.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.entity.MeiziItem
import com.xing.mzitu.net.Api
import com.xing.mzitu.net.ApiClient
import com.xing.mzitu.utils.HtmlParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/5
 *
 * version :
 *
 * desc :
 */
class DataViewModel : ViewModel() {

    private val _meituList = MutableLiveData<List<MeiziItem>>()
            val meituList : LiveData<List<MeiziItem>>
                get()    = _meituList

    init {
        fetchData()
    }


    private fun fetchData() {

        viewModelScope.launch(Dispatchers.IO) {

            val responseData = ApiClient.createService(Api::class.java).getMeituInfo("japan")

            if(responseData.isSuccessful){
                val resultDataList = HtmlParser.parseMeizituList(response = responseData.body()!!, type = 0)

//                resultDataList.forEach {
//                    Log.d("TAG", it.image_url + "," + it.image_desc + "," + it.content_url + "," + it.image_width + "," + it.image_height + "," + it.item_time)
//                }

                _meituList.postValue(resultDataList)

            }else{

                Log.e("TAG","get data error:")

            }
        }
    }

}