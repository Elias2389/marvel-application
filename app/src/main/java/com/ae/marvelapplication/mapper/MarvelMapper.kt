package com.ae.marvelapplication.mapper

import com.ae.marvelappication.data.entity.ResultsItemEntity
import com.ae.marvelapplication.dto.dto.ResultsItem
import com.ae.marvelapplication.dto.dto.Thumbnail
import com.ae.marvelapplication.entity.ThumbnailEntity


/**
 * Mapper to convert from
 * ResultItem to ResultItemEntity
 */
fun ResultsItem.toEntity() = ResultsItemEntity(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail.toThumbnailEntity()
)

/**
 * Mapper to convert from
 * ResultItemEntity to ResultItem
 */
fun ResultsItemEntity.toResultsItem() = ResultsItem(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail.toThumbnail()
)

/**
 * Mapper to convert from
 * thumbnailEntity to thumbnail
 */
fun ThumbnailEntity.toThumbnail() = Thumbnail(
    path = path,
    extension = extension
)

/**
 * Mapper to convert from
 * Thumbnail to ThumbnailEntity
 */
fun Thumbnail.toThumbnailEntity() = ThumbnailEntity(
    path = path,
    extension = extension
)

/**
 * Mapper to convert from
 * list of ResultItemEntity to list of ResultItem
 */
fun List<ResultsItemEntity>.toResultsItem(): List<ResultsItem> {
    return this.map { it.toResultsItem() }
}

/**
 * Mapper to convert from
 * list of ResultsItem to list of ResultsItemEntity
 */
fun List<ResultsItem>.toResultsItemEntity(): List<ResultsItemEntity> {
    return this.map { it.toEntity() }
}