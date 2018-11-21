package com.sangeetsuresh.kotlincommon.interactor

import com.sangeetsuresh.kotlincommon.model.SearchResult
import com.sangeetsuresh.kotlincommon.performGetRequest

const val API_KEY = ""
const val URL = "https://www.omdbapi.com/?apikey=$API_KEY"

interface MainRepository {
    fun searchTitle(text: String, onSuccess: (SearchResult?) -> Unit)
}

class MainRepositoryImpl : MainRepository {
    override fun searchTitle(text: String, onSuccess: (SearchResult?) -> Unit) {
        performGetRequest("$URL&s=$text", SearchResult.serializer(), onSuccess)
    }
}