package com.xing.mzitu.net

import android.util.Log
import com.xing.mzitu.utils.C
import okhttp3.Dispatcher
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

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

object ApiClient {

    private val okHttpClient by lazy { OkHttpClient() }

    private val retrofit: Retrofit by lazy {
        Log.e("AppClient", "Creating Retrofit Client")

        val builder = Retrofit.Builder()
            .baseUrl(C.BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client: OkHttpClient = okHttpClient.newBuilder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
        builder.client(client).build()
    }

    fun <T> createService(tClass: Class<T>): T {
        return retrofit.create(tClass)
    }

}
