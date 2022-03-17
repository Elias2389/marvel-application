package com.ae.requestmanager.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ThumbnailServer(
    @Json(name = "path")
    val path: String = "",
    @Json(name = "extension")
    val extension: String = ""
)