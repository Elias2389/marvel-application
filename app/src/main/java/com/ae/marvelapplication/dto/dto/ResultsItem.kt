package com.ae.marvelappication.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultsItem(
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail = Thumbnail(),
    @SerializedName("urls")
    val urls: List<UrlsItem> = emptyList(),
    @SerializedName("stories")
    val stories: Stories = Stories(),
    @SerializedName("series")
    val series: Series = Series(),
    @SerializedName("comics")
    val comics: Comics = Comics(),
    @SerializedName("name")
    val name: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("modified")
    val modified: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("resourceURI")
    val resourceURI: String = "",
    @SerializedName("events")
    val events: Events = Events()
) : Parcelable