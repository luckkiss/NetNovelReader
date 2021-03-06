package com.netnovelreader

import android.app.Application
import android.content.Context
import android.os.Environment
import android.support.multidex.MultiDex
import com.netnovelreader.data.local.ReaderDbManager
import com.squareup.leakcanary.LeakCanary
import kotlinx.coroutines.experimental.ThreadPoolDispatcher
import kotlinx.coroutines.experimental.newFixedThreadPoolContext

class ReaderApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        LeakCanary.install(this)                  //内存泄漏检测框架LeakCanary全局初始化操作
        ReaderDbManager.initDatabase(this)
        threadPool = newFixedThreadPoolContext(
                Runtime.getRuntime().availableProcessors() * 2 / 3, "appPoolContext"
        )
        dirPath = if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
            Environment.getExternalStorageDirectory().path + "/netnovelreader"
        } else {
            filesDir.absolutePath
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    companion object {
        lateinit var threadPool: ThreadPoolDispatcher
        lateinit var dirPath: String
    }
}