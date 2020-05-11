package com.xing.mzitu

import android.app.Application
import timber.log.Timber

/**
 * created by xinghe
 *
 * 纵然万劫不复，纵然相思入骨，我依然待你眉眼如初，岁月如故。
 *
 * date : 2020/4/29
 *
 * version :
 *
 * desc :
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())
        Timber.d("message")
    }
}