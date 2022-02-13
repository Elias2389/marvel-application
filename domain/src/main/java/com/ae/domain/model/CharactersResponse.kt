package com.ae.domain.model


data class CharactersResponse(
    val copyright: String = "",
    val code: Int = 0,
    val data: Data = Data(),
    val attributionHTML: String = "",
    val attributionText: String = "",
    val etag: String = "",
    val status: String = ""
)