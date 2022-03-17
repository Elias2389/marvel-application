package com.ae.databasemanager.util

import com.ae.databasemanager.entity.ResultsItemEntity
import com.ae.databasemanager.entity.ThumbnailEntity

const val mockOffset = 0
const val mockLimit = 10
const val mockCharacterId = 1011334
const val mockName = "Test User"

val mockResultsItemEntity = ResultsItemEntity(
    id = 12334,
    name = mockName,
    thumbnail = ThumbnailEntity()
)

val mockResultsItemEntityList = listOf<ResultsItemEntity>(
    mockResultsItemEntity.copy(id = 1),
    mockResultsItemEntity.copy(id = 2)
)