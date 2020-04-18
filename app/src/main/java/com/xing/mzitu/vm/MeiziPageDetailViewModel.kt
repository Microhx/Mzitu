package com.xing.mzitu.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.xing.mzitu.entity.MeiziDetailItem
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
 * date : 2020/4/15
 *
 * version :
 *
 * desc : 妹子图详情页面
 */
class MeiziPageDetailViewModel : BaseViewModel() {

    //show the process when loading
    private val _loadingTip : MutableLiveData<String> = MutableLiveData()
    val loadingTip : LiveData<String> = _loadingTip

    //show the result list
    private val _loadingDetailList : MutableLiveData<List<MeiziDetailItem>> = MutableLiveData()
    val loadingDetailList : LiveData<List<MeiziDetailItem>> = _loadingDetailList

    fun fetch(url:String) {
        _loadingTip.postValue("开始加载url:$url")

        viewModelScope.launch(Dispatchers.IO) {

            val responseData = ApiClient.createService(Api::class.java).getMeituDetail(url,1)
            if(responseData.isSuccessful){
                _loadingTip.postValue("获取首页数据成功，开始解析:")
                val responseDataString = responseData.body()

                val meizituDetail = HtmlParser.parseMeizituDetail(responseDataString, 1, 0, true)
                if(meizituDetail.pageTotal > 0){
                    val resultDetailList = ArrayList<MeiziDetailItem>()
                    resultDetailList.add(meizituDetail)

                    _loadingTip.postValue("第一页解析成功，返回图片值")

                    //第二页已经解析了
                    for(m in 2..meizituDetail.pageTotal){
                        val pageResponse = ApiClient.createService(Api::class.java).getMeituDetail(url,m)
                        if(pageResponse.isSuccessful){
                            val pageResponseString = pageResponse.body()
                            val pageMeizituDetail = HtmlParser.parseMeizituDetail(pageResponseString, m, meizituDetail.pageTotal, false)

                            resultDetailList.add(pageMeizituDetail)

                            _loadingTip.postValue("第$m 页解析成功，还剩${meizituDetail.pageTotal - m}页数据等待解析")

                        }else{
                            _loadingTip.postValue("第$m 页数据解析失败,code:${pageResponse.code()}")
                        }
                    }

                    _loadingTip.postValue("共${meizituDetail.pageTotal}页数据解析成功，请欣赏")

                    _loadingDetailList.postValue(resultDetailList)

                }else{
                    _loadingTip.postValue("获取妹子图totalPage失败，请检查程序")
                }

            }else{
                _loadingTip.postValue("获取数据异常，异常码为:${responseData.code()}")
            }
        }
    }

}