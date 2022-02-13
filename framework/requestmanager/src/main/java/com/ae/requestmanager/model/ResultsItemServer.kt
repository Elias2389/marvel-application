package com.ae.requestmanager.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResultsItemServer(
    @Json(name = "thumbnail")
    val thumbnail: ThumbnailServer = ThumbnailServer(),
    @Json(name = "urls")
    val urls: List<UrlsItemServer> = emptyList(),
    @Json(name = "stories")
    val stories: StoriesServer = StoriesServer(),
    @Json(name = "series")
    val series: SeriesServer = SeriesServer(),
    @Json(name = "comics")
    val comics: ComicsServer = ComicsServer(),
    @Json(name = "name")
    val name: String = "",
    @Json(name = "description")
    val description: String = "",
    @Json(name = "modified")
    val modified: String = "",
    @Json(name = "id")
    val id: Int = 0,
    @Json(name = "resourceURI")
    val resourceURI: String = "",
    @Json(name = "events")
    val events: EventsServer = EventsServer()
)