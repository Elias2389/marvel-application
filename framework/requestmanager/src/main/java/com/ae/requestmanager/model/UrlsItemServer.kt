package com.ae.requestmanager.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UrlsItemServer(
    @Json(name = "type")
    val type: String = "",
    @Json(name = "url")
    val url: String = ""
)