package com.ae.databasemanager.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ae.databasemanager.entity.ResultsItemEntity

@Database(entities = [ResultsItemEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun resultItemDao(): ResultItemDao
}
