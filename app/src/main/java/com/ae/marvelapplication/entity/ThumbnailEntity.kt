package com.ae.marvelapplication.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

@Entity(tableName = "thumbnail")
data class ThumbnailEntity(
    @Json(name = "path")
    @ColumnInfo(name = "path")
    val path: String = "",
    @Json(name = "extension")
    @ColumnInfo(name = "extension")
    val extension: String = ""
)