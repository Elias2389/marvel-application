package com.ae.marvelapplication.dto.dto

import com.squareup.moshi.Json

data class MarvelResponse(
    @Json(name = "copyright")
    val copyright: String = "",
    @Json(name = "code")
    val code: Int = 0,
    @Json(name = "data")
    val data: Data = Data(),
    @Json(name = "attributionHTML")
    val attributionHTML: String = "",
    @Json(name = "attributionText")
    val attributionText: String = "",
    @Json(name = "etag")
    val etag: String = "",
    @Json(name = "status")
    val status: String = ""
)