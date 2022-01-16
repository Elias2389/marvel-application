package com.ae.marvelapplication.data.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ae.marvelappication.data.entity.ResultsItemEntity

@Database(entities = [ResultsItemEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultItemDao(): ResultItemDao
}
