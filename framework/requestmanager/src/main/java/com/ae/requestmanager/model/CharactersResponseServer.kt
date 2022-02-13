package com.ae.requestmanager.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharactersResponseServer(
    @Json(name = "copyright")
    val copyright: String = "",
    @Json(name = "code")
    val code: Int = 0,
    @Json(name = "data")
    val data: DataServer = DataServer(),
    @Json(name = "attributionHTML")
    val attributionHTML: String = "",
    @Json(name = "attributionText")
    val attributionText: String = "",
    @Json(name = "etag")
    val etag: String = "",
    @Json(name = "status")
    val status: String = ""
)