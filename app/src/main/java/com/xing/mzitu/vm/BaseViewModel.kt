package com.xing.mzitu.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xing.mzitu.entity.MeiziItem
import com.xing.mzitu.entity.RequestLogicData
import com.xing.mzitu.logic.LoadingState
import com.xing.mzitu.utils.BaseCommonUtils
import com.xing.mzitu.utils.HtmlParser
import retrofit2.Response
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
open class BaseViewModel : ViewModel() {

    private val _loading : MutableLiveData<LoadingState> = MutableLiveData()
    val loading : LiveData<LoadingState> = _loading

    fun checkResponseData(data: Response<String>, requestData:RequestLogicData){
        Log.d("TAG","response code: ${data.code()}")

        if(data.isSuccessful){
            val stringData = data.body()
            val meiziItemList = HtmlParser.parseMeizituList(response = stringData!!, type = 0)

            if(BaseCommonUtils.checkCollection(meiziItemList)){
                postSuccessDataValue(meiziItemList, requestData)
                _loading.postValue(LoadingState.COMPLETED)

            }else{
                postEmptyDataValue(requestData)
                _loading.postValue(LoadingState.EMPTY)
            }

        }else{
            _loading.postValue(LoadingState.ERROR)
        }
    }

    protected fun postStartValue(){
        _loading.postValue(LoadingState.START)
    }

    open fun postSuccessDataValue(list: ArrayList<MeiziItem>, data: RequestLogicData) {}

    open fun postEmptyDataValue(data: RequestLogicData) {}

}