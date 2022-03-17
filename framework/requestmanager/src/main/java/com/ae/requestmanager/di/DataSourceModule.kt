package com.ae.requestmanager.di

import com.ae.data.datasource.CharactersRemoteDataSource
import com.ae.data.service.CharacterService
import com.ae.requestmanager.datasource.CharacterRemoteDataSourceImpl
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
}