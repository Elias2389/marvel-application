package com.ae.marvelapplication.util

import com.ae.marvelapplication.dto.dto.Comics
import com.ae.marvelapplication.dto.dto.Data
import com.ae.marvelapplication.dto.dto.Events
import com.ae.marvelapplication.dto.dto.CharactersResponse
import com.ae.marvelapplication.dto.dto.ResultsItem
import com.ae.marvelapplication.dto.dto.Series
import com.ae.marvelapplication.dto.dto.Stories
import com.ae.marvelapplication.dto.dto.Thumbnail
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

const val mockOffset = 0
const val mockLimit = 10
const val mockCharacterId = 1011334

val characterMock: ResultsItem = ResultsItem(
    id = 12334,
    thumbnail = Thumbnail(),
    comics = Comics(),
    series = Series(),
    events = Events(),
    stories = Stories(),
    urls = emptyList()
)

val characterListMock = listOf(
    characterMock.copy(id = 1),
    characterMock.copy(id = 2)
)

val mockCharacterResponse = CharactersResponse()
    .copy(
        data = Data(
            offset = mockOffset,
            limit = mockLimit,
            results = characterListMock
        )
    )

val mockCharacterResponseEmptyList = CharactersResponse()
    .copy(
        data = Data(
            offset = mockOffset,
            limit = mockLimit,
            results = emptyList()
        )
    )

fun String.JsonToCharacterResponse(): CharactersResponse {
    val moshi = Moshi.Builder().build()
    val jsonAdapter = moshi.adapter(CharactersResponse::class.java)
    return jsonAdapter.fromJson(this) ?: CharactersResponse()
}