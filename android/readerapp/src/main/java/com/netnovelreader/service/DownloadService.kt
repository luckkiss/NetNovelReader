package com.netnovelreader.service

import android.app.IntentService
import android.app.NotificationManager
import android.content.Intent
import android.support.v4.app.NotificationCompat
import com.netnovelreader.R
import com.netnovelreader.ReaderApplication
import com.netnovelreader.ReaderApplication.Companion.threadPool
import com.netnovelreader.bean.ChapterBean
import com.netnovelreader.common.toast
import com.netnovelreader.data.CatalogManager
import com.netnovelreader.data.ChapterManager
import com.netnovelreader.data.local.ReaderDbManager
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch
import java.io.File
import java.io.IOException
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.atomic.AtomicInteger

class DownloadService : IntentService {
    constructor() : super("DownloadService")
    constructor(name: String) : super(name)

    private var mNotificationManager: NotificationManager? = null
    private var builder: NotificationCompat.Builder? = null
    private val NOTIFYID = 1599407175
    lateinit var lock: LinkedBlockingQueue<Int>

    @Volatile
    private var max = 0                 //下载总数
    private var progress = AtomicInteger()     //下载完成数（包括失败的下载）
    private var failed = AtomicInteger()              //失败的下载数
    @Volatile
    private var remainder = 0            //待下载的书籍数

    override fun onCreate() {
        super.onCreate()
        openNotification()
        lock = LinkedBlockingQueue()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        remainder++
        return super.onStartCommand(intent, flags, startId)
    }

    @Synchronized
    override fun onHandleIntent(intent: Intent?) {
        remainder--
        val tableName = intent?.getStringExtra("tableName")
        val catalogUrl = intent?.getStringExtra("catalogurl")
        if (intent == null || tableName.isNullOrEmpty() || catalogUrl.isNullOrEmpty()) return
        getList(tableName!!, catalogUrl!!).apply { max = this.size }.forEach { bean ->
            val downloader = ChapterManager(0, tableName, 0)
            //获取要下载的章节列表
            launch(threadPool) {
                try {
                    downloader.writToDisk(bean, downloader.getChapterTxt(bean))     //下载每一章
                    progress.incrementAndGet()
                } catch (e: IOException) {
                    failed.incrementAndGet()
                    e.printStackTrace()
                }
                synchronized(IntentService::class.java) {
                    updateNotification(progress.get(), max)
                    if (progress.get() + failed.get() == max) lock.offer(1)   //下载完成，取消阻塞
                }
            }
        }
        lock.take()  //阻塞住线程，一次只下载一本书
    }

    override fun onDestroy() {
        super.onDestroy()
        mNotificationManager?.cancel(NOTIFYID)
        if (failed.get() > 0) {
            toast(getString(R.string.downloadfailed).replace("nn", "$failed"))
        }
    }

    private fun openNotification() {
        builder = NotificationCompat.Builder(this, "reader")
                .setTicker(getString(R.string.app_name))
                .setContentTitle(getString(R.string.prepare_download))
                .setSmallIcon(R.drawable.notification_icon)
        mNotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        mNotificationManager?.notify(NOTIFYID, builder?.build())
    }

    fun updateNotification(progress: Int, max: Int) {
        val str = if (remainder != 0) ",${getString(R.string.wait4download)}"
                .replace("nn", "$remainder") else ""
        builder?.setProgress(max, progress, false)
                ?.setContentTitle("${getString(R.string.downloading)}:$progress/$max$str")
        launch(UI) { mNotificationManager?.notify(NOTIFYID, builder?.build()) }
    }

    fun getList(tableName: String, url: String): ArrayList<ChapterBean> {
        val list = ArrayList<ChapterBean>()
        try {
            CatalogManager.download(tableName, url)
        } catch (e: IOException) {
            e.printStackTrace()
            return list
        }
        val path = "${ReaderApplication.dirPath}/$tableName".apply { File(this).mkdirs() }
        ReaderDbManager.getChapterNameAndUrl(tableName, 0).forEach {
            list.add(ChapterBean(tableName, path, it.key, it.value))
        }
        return list
    }
}