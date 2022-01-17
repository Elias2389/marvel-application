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

const val mockOffset = 0
const val mockLimit = 10
const val mockCharacterId = 1011334
const val mockName = "Test User"

val mockCharacter: ResultsItem = ResultsItem(
    id = 12334,
    name = mockName,
    thumbnail = Thumbnail(),
    comics = Comics(),
    series = Series(),
    events = Events(),
    stories = Stories(),
    urls = emptyList()
)

val mockCharacterList = listOf(
    mockCharacter.copy(id = 1),
    mockCharacter.copy(id = 2)
)

val mockCharacterDetail = listOf(
    mockCharacter.copy(id = 1)
)

val mockCharacterResponse = CharactersResponse()
    .copy(
        data = Data(
            offset = mockOffset,
            limit = mockLimit,
            results = mockCharacterList
        )
    )

val mockCharacterDetailResponse = CharactersResponse()
    .copy(
        data = Data(
            results = mockCharacterDetail
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