package com.xing.mzitu.external

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/30
 *
 * version :
 *
 * desc :
 */
class WithLoadImage  {

    fun fetchLoadImage() {

        GlobalScope.launch(Dispatchers.Main) {

            val bitmap = withContext(Dispatchers.IO) {
                getBitmapByUrl()
            }

            Timber.d("load bitmap By $bitmap")
        }

        //Thread.currentThread().state

        Thread().run()


    }

    private fun getBitmapByUrl(): Bitmap = BitmapFactory.decodeResource(null,0)


}