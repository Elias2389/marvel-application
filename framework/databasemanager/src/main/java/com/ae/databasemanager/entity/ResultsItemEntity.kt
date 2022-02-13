package com.ae.databasemanager.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ae.databasemanager.entity.ThumbnailEntity

@Entity(tableName = "character", indices = [Index(value = ["id"], unique = true)])
data class ResultsItemEntity(
    @PrimaryKey(autoGenerate = true)
    val idCharacter: Int = 0,
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @Embedded
    val thumbnail: ThumbnailEntity = ThumbnailEntity(),
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "description")
    val description: String = "",
    @ColumnInfo(name = "created_at")
    val createAt: Long = System.currentTimeMillis()
)