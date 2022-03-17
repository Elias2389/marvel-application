package com.ae.marvelapplication.mapper

import com.ae.domain.model.ResultsItem
import com.ae.domain.model.Thumbnail
import com.ae.marvelapplication.parcelable.ResultsItemParcelable
import com.ae.marvelapplication.parcelable.ThumbnailParcelable

/**
 * Mapper to convert from
 * ResultItem to Parcelable
 */
fun ResultsItem.toParcelable() = ResultsItemParcelable(
    id = id,
    name = name,
    description = description,
    thumbnail = thumbnail.toThumbnailParcelable()
)

/**
 * Mapper to convert from
 * thumbnailEntity to thumbnail
 */
fun Thumbnail.toThumbnailParcelable() = ThumbnailParcelable(
    path = path,
    extension = extension
)