package com.ae.marvelapplication.core.di

import android.content.Context
import androidx.room.Room
import com.ae.marvelapplication.data.dao.AppDatabase
import com.ae.marvelapplication.data.dao.ResultItemDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalServiceModule {

    private const val DB_NAME: String = "character_db"

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Singleton
    @Provides
    fun provideResultItemDao(appDatabase: AppDatabase): ResultItemDao {
        return appDatabase.resultItemDao()
    }
}