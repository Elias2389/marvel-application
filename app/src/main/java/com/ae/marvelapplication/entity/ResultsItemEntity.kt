package com.ae.marvelappication.data.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ae.marvelapplication.entity.ThumbnailEntity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "character")
data class ResultsItemEntity(
    @SerializedName("id_character")
    @PrimaryKey(autoGenerate = true)
    val idCharacter: Int = 0,
    @SerializedName("id")
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @SerializedName("thumbnail")
    @Embedded
    val thumbnail: ThumbnailEntity = ThumbnailEntity(),
    @SerializedName("name")
    @ColumnInfo(name = "name")
    val name: String = "",
    @SerializedName("description")
    @ColumnInfo(name = "description")
    val description: String = "",
    @SerializedName("created_at")
    @ColumnInfo(name = "created_at")
    var createAt: Long = System.currentTimeMillis()
)