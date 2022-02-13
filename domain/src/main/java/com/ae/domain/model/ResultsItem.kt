package com.ae.domain.model

data class ResultsItem(
    val thumbnail: Thumbnail = Thumbnail(),
    val urls: List<UrlsItem> = emptyList(),
    val stories: Stories = Stories(),
    val series: Series = Series(),
    val comics: Comics = Comics(),
    val name: String = "",
    val description: String = "",
    val modified: String = "",
    val id: Int = 0,
    val resourceURI: String = "",
    val events: Events = Events()
)