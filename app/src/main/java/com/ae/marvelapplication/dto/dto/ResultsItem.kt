package com.ae.marvelapplication.dto.dto

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ResultsItem(
    @Json(name = "thumbnail")
    val thumbnail: Thumbnail = Thumbnail(),
    @Json(name = "urls")
    val urls: List<UrlsItem> = emptyList(),
    @Json(name = "stories")
    val stories: Stories = Stories(),
    @Json(name = "series")
    val series: Series = Series(),
    @Json(name = "comics")
    val comics: Comics = Comics(),
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
    val events: Events = Events()
) : Parcelable