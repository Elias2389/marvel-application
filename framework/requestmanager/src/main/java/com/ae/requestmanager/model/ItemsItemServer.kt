package com.ae.requestmanager.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ItemsItemServer(
    val name: String = "",
    val resourceURI: String = ""
)