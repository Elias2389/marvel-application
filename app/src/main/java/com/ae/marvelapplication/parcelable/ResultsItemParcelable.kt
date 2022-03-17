package com.ae.marvelapplication.parcelable

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ResultsItemParcelable(
    val thumbnail: ThumbnailParcelable = ThumbnailParcelable(),
    val urls: List<UrlsItemParcelable> = emptyList(),
    val stories: StoriesParcelable = StoriesParcelable(),
    val series: SeriesParcelable = SeriesParcelable(),
    val comics: ComicsParcelable = ComicsParcelable(),
    val name: String = "",
    val description: String = "",
    val modified: String = "",
    val id: Int = 0,
    val resourceURI: String = "",
    val events: EventsParcelable = EventsParcelable()
) : Parcelable
