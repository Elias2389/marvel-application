package com.ae.data.util

import com.ae.domain.model.CharactersResponse
import com.ae.domain.model.Comics
import com.ae.domain.model.Data
import com.ae.domain.model.Events
import com.ae.domain.model.ItemsItem
import com.ae.domain.model.ResultsItem
import com.ae.domain.model.Series
import com.ae.domain.model.Stories
import com.ae.domain.model.Thumbnail

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

val mockCharacterComics = listOf(
    mockCharacter.copy(id = 1, comics = Comics(items = listOf(ItemsItem(name = "first"))))
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