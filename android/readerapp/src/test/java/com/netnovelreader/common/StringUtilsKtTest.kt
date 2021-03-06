package com.netnovelreader.common

import org.junit.Assert
import org.junit.Test

class StringUtilsKtTest {

    @Test
    fun url2Hostname() {
        val list = listOf(
                "http://m3.www.hello.world/nihao/1/2/3.html",
                "http://www.hello.world/nihao/1/2/3.html",
                "http://hello.world/nihao/1/2/3.html",
                "http://world/nihao/1/2/3.html"
        )
        val result = list.map { com.netnovelreader.common.url2Hostname(it) }
        for (i in 0 until list.size) {
            println("${list[i]} --- ${result[i]}")
        }
    }

    @Test
    fun fixUrl() {
        val reference = "http://www.23zw.me/search/result.html"
        val src = arrayOf(
                "//www.23zw.me/3/hello/index.html",
                "/index.html",
                "index.html",
                "11111111/hello/index.html",
                "/11111111/hello/index.html",
                "search/index.html"
        )

        val actural = src.map { com.netnovelreader.common.fixUrl(reference, it) }

        val expected = arrayOf(
                "http://www.23zw.me/3/hello/index.html",
                "http://www.23zw.me/search/index.html",
                "http://www.23zw.me/search/index.html",
                "http://www.23zw.me/search/11111111/hello/index.html",
                "http://www.23zw.me/search/11111111/hello/index.html",
                "http://www.23zw.me/search/index.html"
        )
        for (i in 0 until src.size) {
            println("${src[i]} --- ${actural[i]} --- ${expected[i]}")
            Assert.assertEquals(actural[i], expected[i])
        }
    }
}