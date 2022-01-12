package com.ae.marvelappication.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Comics(
    @SerializedName("collectionURI")
    val collectionURI: String = "",
    @SerializedName("available")
    val available: Int = 0,
    @SerializedName("returned")
    val returned: Int = 0,
    @SerializedName("items")
    val items: List<ItemsItem> = emptyList()
): Parcelable