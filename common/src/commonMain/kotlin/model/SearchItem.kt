package com.sangeetsuresh.kotlincommon.model

import kotlinx.serialization.Optional
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchItem(
    @SerialName("Title") val title: String,
    @SerialName("Year") val year: String,
    @SerialName("imdbID") val imdbID: String,
    @SerialName("Type") val type: String,
    @SerialName("Poster") val poster: String
)

@Serializable
data class SearchResult(
    @Optional @SerialName("Search") val data: List<SearchItem>? = null,
    @Optional @SerialName("Response") val response: Boolean = true,
    @Optional @SerialName("Error") val error: String? = null
)
