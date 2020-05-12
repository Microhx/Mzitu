package com.xing.mzitu.proxy

import okhttp3.*
import timber.log.Timber
import java.io.IOException
import java.net.InetSocketAddress
import java.net.Proxy
import java.util.concurrent.TimeUnit

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/5/12
 *
 * version :
 *
 * desc :
 */
fun testProxy() : Response{

    val builder = OkHttpClient.Builder()
    builder.connectTimeout(8000, TimeUnit.SECONDS)
    builder.readTimeout(8000, TimeUnit.SECONDS)

    val proxy = Proxy(Proxy.Type.HTTP, InetSocketAddress("218.75.158.153",3128))

    builder.proxy(proxy)

    val client = builder.build()

    val request = Request.Builder()
                  .header("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.132 Safari/537.36")
                  .header("origin","https://www.mzitu.com")
                  .header("referer","https://www.mzitu.com")
                  .url("https://www.mzitu.com/229725/3").get().build()

    return client.newCall(request = request).execute()

}