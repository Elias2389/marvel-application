package com.ae.marvelapplication.dto.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Comics(
    @Json(name = "collectionURI")
    val collectionURI: String = "",
    @Json(name = "available")
    val available: Int = 0,
    @Json(name = "returned")
    val returned: Int = 0,
    @Json(name = "items")
    val items: List<ItemsItem> = emptyList()
) : Parcelable