package com.ae.marvelapplication.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.ae.marvelapplication.entity.ThumbnailEntity
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

@Entity(tableName = "character", indices = [Index(value = ["id"], unique = true)])
data class ResultsItemEntity(
    @Json(name = "id_character")
    @PrimaryKey(autoGenerate = true)
    val idCharacter: Int = 0,
    @Json(name ="id")
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @Json(name ="thumbnail")
    @Embedded
    val thumbnail: ThumbnailEntity = ThumbnailEntity(),
    @Json(name ="name")
    @ColumnInfo(name = "name")
    val name: String = "",
    @Json(name ="description")
    @ColumnInfo(name = "description")
    val description: String = "",
    @Json(name ="created_at")
    @ColumnInfo(name = "created_at")
    var createAt: Long = System.currentTimeMillis()
)