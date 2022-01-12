package com.ae.marvelappication.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class UrlsItem(
    @SerializedName("type")
    val type: String = "",
    @SerializedName("url")
    val url: String = ""
): Parcelable