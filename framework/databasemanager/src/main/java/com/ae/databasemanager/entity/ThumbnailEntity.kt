package com.ae.databasemanager.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "thumbnail")
data class ThumbnailEntity(
    @ColumnInfo(name = "path")
    val path: String = "",
    @ColumnInfo(name = "extension")
    val extension: String = ""
)