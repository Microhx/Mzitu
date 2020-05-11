package com.xing.mzitu.net

import com.xing.mzitu.utils.C
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

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
interface Api {

    @Headers(
        C.ORIGIN,
        C.REFERER,
        C.USER_AGENT
    )
    @GET("{url}/page/{page}")
    suspend fun getMeituInfo(@Path("url") url:String?, @Path("page") page:Int) : Response<String>

    @Headers(
        C.ORIGIN,
        C.REFERER,
        C.USER_AGENT)
    @GET("{url}/{page}")
    suspend fun getMeituDetail(@Path("url") url:String, @Path("page") page:Int) : Response<String>

    suspend fun getNetWorkOneRequest() : Response<String>

    suspend fun getNetWorkTwoRequest() : Response<Any>

    suspend fun getDataOfList(): List<String>

    suspend fun getData() : String





}