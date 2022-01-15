package com.ae.marvelapplication.dto.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class UrlsItem(
    @Json(name = "type")
    val type: String = "",
    @Json(name = "url")
    val url: String = ""
) : Parcelable