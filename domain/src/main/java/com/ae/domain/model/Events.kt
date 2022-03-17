package com.ae.domain.model

data class Events(
    val collectionURI: String = "",
    val available: Int = 0,
    val returned: Int = 0,
    val items: List<ItemsItem> = emptyList()
)