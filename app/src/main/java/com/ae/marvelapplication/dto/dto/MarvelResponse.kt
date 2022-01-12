package com.ae.marvelapplication.dto.dto

import com.ae.marvelappication.dto.Data
import com.google.gson.annotations.SerializedName

data class MarvelResponse(
    @SerializedName("copyright")
    val copyright: String = "",
    @SerializedName("code")
    val code: Int = 0,
    @SerializedName("data")
    val data: Data = Data(),
    @SerializedName("attributionHTML")
    val attributionHTML: String = "",
    @SerializedName("attributionText")
    val attributionText: String = "",
    @SerializedName("etag")
    val etag: String = "",
    @SerializedName("status")
    val status: String = ""
)