package com.netnovelreader.bean

import android.databinding.ObservableField
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.netnovelreader.ReaderApplication
import com.netnovelreader.data.local.db.ShelfBean
import java.io.File

data class BookInfo(
        var bookname: ObservableField<String>,
        var latestChapter: ObservableField<String>,
        var downloadURL: ObservableField<String>,
        var bitmap: ObservableField<Bitmap?>,
        var isUpdate: ObservableField<String>
) {
    companion object {
        fun fromShelfBean(bean: ShelfBean) = BookInfo(
                ObservableField(bean.bookName ?: ""),
                ObservableField(bean.latestChapter ?: ""),
                ObservableField(bean.downloadUrl ?: ""),
                ObservableField(getBitmap(bean.bookName ?: "")),
                ObservableField(bean.isUpdate ?: "")
        )

        //书架将要显示的书籍封面图片
        private fun getBitmap(bookname: String): Bitmap? =
                File("${ReaderApplication.dirPath}/$bookname", "$bookname.png")
                        .takeIf { it.exists() }
                        ?.let { BitmapFactory.decodeFile(it.path) }
    }
}