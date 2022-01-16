package com.ae.marvelapplication.core.di

import com.ae.marvelapplication.data.dao.ResultItemDao
import com.ae.marvelapplication.data.datasource.character.CharacterLocalDataSource
import com.ae.marvelapplication.data.datasource.character.CharacterLocalDataSourceImpl
import com.ae.marvelapplication.data.datasource.character.CharactersRemoteDataSource
import com.ae.marvelapplication.data.datasource.character.CharacterRemoteDataSourceImpl
import com.ae.marvelapplication.data.service.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Singleton
    @Provides
    fun provideCharacterListRemoteDataSource(
        characterService: CharacterService
    ): CharactersRemoteDataSource = CharacterRemoteDataSourceImpl(
        characterService
    )

    @Singleton
    @Provides
    fun provideCharacterListLocalDataSource(
        characterDao: ResultItemDao
    ): CharacterLocalDataSource = CharacterLocalDataSourceImpl(characterDao)
}