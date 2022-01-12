package com.ae.marvelapplication.dto.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Thumbnail(
    @Json(name = "path")
    val path: String = "",
    @Json(name = "extension")
    val extension: String = ""
) : Parcelable