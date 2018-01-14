package com.netnovelreader.data.network

import org.jsoup.Jsoup

/**
 * Created by yangbo on 18-1-14.
 */
class ParseHtml {
    fun getChapter(url: String, selector: String): String{
        val chapter = Jsoup.connect(url).get().select(selector)
        var txt = "    " + chapter.text().replace(" ", "\n\n    ")
        return txt
    }

    fun getCatalog(url: String, selector: String): LinkedHashMap<String, String>{
        var catalog = LinkedHashMap<String, String>()
        var list = Jsoup.connect(url).get().select(selector).select("a")
        list.forEach {
            var link = it.attr("href")
            if(!link.contains("//")){
                link = url.substring(0, url.lastIndexOf('/') + 1) + link
            }else if(link.startsWith("//")){
                link = "http:" + link
            }
            catalog.put(it.text(), link)
        }
        return catalog
    }
}