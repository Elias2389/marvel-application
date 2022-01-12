package com.ae.marvelapplication.dto.dto

import com.squareup.moshi.Json

data class Data(
    @Json(name = "total")
    val total: Int = 0,
    @Json(name = "offset")
    val offset: Int = 0,
    @Json(name = "limit")
    val limit: Int = 0,
    @Json(name = "count")
    val count: Int = 0,
    @Json(name = "results")
    val results: List<ResultsItem> = emptyList()
)