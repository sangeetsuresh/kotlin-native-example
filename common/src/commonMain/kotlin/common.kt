package com.sangeetsuresh.kotlincommon

import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.JSON


fun <T> performGetRequest(url: String, serializer: KSerializer<T>, block: (T?) -> Unit) {
    HttpDelegate.fetchUrl(url) {
        it?.let {
            val data = JSON.nonstrict.parse(serializer, it)
            block(data)
        } ?: block(null)
    }
}

expect class HttpDelegate {
    companion object {
        fun fetchUrl(url: String, block: (String?) -> Unit)
    }
}

