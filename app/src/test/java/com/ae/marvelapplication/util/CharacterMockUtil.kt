package com.ae.marvelapplication.util

import com.ae.requestmanager.model.CharactersResponseServer
import com.ae.domain.model.Comics
import com.ae.domain.model.Data
import com.ae.domain.model.Events
import com.ae.domain.model.ItemsItem
import com.ae.domain.model.ResultsItem
import com.ae.domain.model.Series
import com.ae.domain.model.Stories
import com.ae.domain.model.Thumbnail
import com.ae.requestmanager.model.ComicsServer
import com.ae.requestmanager.model.DataServer
import com.ae.requestmanager.model.EventsServer
import com.ae.requestmanager.model.ItemsItemServer
import com.ae.requestmanager.model.ResultsItemServer
import com.ae.requestmanager.model.SeriesServer
import com.ae.requestmanager.model.StoriesServer
import com.ae.requestmanager.model.ThumbnailServer

const val mockOffset = 0
const val mockLimit = 10
const val mockCharacterId = 1011334
const val mockName = "Test User"

val mockCharacter: ResultsItemServer = ResultsItemServer(
    id = 12334,
    name = mockName,
    thumbnail = ThumbnailServer(),
    comics = ComicsServer(),
    series = SeriesServer(),
    events = EventsServer(),
    stories = StoriesServer() s(),
    urls = emptyList()
)

val mockCharacterList = listOf(
    mockCharacter.copy(id = 1),
    mockCharacter.copy(id = 2)
)

val mockCharacterComics = listOf(
    mockCharacter.copy(id = 1, comics = ComicsServer(items = listOf(ItemsItemServer(name = "first"))))
)

val mockCharacterDetail = listOf(
    mockCharacter.copy(id = 1)
)

val mockCharacterResponse = CharactersResponseServer()
    .copy(
        data = DataServer(
            offset = mockOffset,
            limit = mockLimit,
            results = mockCharacterList
        )
    )

val mockCharacterDetailResponse = CharactersResponseServer()
    .copy(
        data = DataServer(
            results = mockCharacterDetail
        )
    )

val mockCharacterResponseEmptyList = CharactersResponseServer()
    .copy(
        data = DataServer(
            offset = mockOffset,
            limit = mockLimit,
            results = emptyList()
        )
    )

fun String.JsonToCharacterResponse(): CharactersResponseServer {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(CharactersResponseServer::class.java)
    return jsonAdapter.fromJson(this) ?: CharactersResponseServer()
}