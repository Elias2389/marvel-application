package com.ae.marvelapplication.dto.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class ItemsItem(
    @Json(name = "name")
    val name: String = "",
    @Json(name = "resourceURI")
    val resourceURI: String = ""
) : Parcelable