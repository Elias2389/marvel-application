package com.ae.requestmanager.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EventsServer(
    @Json(name = "collectionURI")
    val collectionURI: String = "",
    @Json(name = "available")
    val available: Int = 0,
    @Json(name = "returned")
    val returned: Int = 0,
    @Json(name = "items")
    val items: List<ItemsItemServer> = emptyList()
)