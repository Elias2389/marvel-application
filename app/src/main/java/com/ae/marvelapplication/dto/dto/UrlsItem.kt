package com.ae.marvelapplication.dto.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrlsItem(
    @Json(name = "type")
    val type: String = "",
    @Json(name = "url")
    val url: String = ""
) : Parcelable