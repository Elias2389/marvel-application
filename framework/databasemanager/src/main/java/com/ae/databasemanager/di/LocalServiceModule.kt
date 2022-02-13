package com.ae.databasemanager.di

import android.content.Context
import androidx.room.Room
import com.ae.data.datasource.CharacterLocalDataSource
import com.ae.databasemanager.dao.AppDatabase
import com.ae.databasemanager.dao.ResultItemDao
import com.ae.databasemanager.datasource.CharacterLocalDataSourceImpl
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

    @Singleton
    @Provides
    fun provideCharacterListLocalDataSource(
        characterDao: ResultItemDao
    ): CharacterLocalDataSource = CharacterLocalDataSourceImpl(characterDao)
}