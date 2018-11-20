package com.sangeetsuresh.kotlincommon


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.net.URL

actual class HttpDelegate {
    actual companion object {
        actual fun fetchUrl(url: String, block: (String?) -> Unit) {
            val request = GlobalScope.async(Dispatchers.IO) {
                URL(url).readText()
            }
            GlobalScope.launch(Dispatchers.Main) {
                block(request.await())
            }
        }
    }
}