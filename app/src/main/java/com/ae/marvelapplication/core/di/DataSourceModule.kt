package com.ae.marvelapplication.core.di

import com.ae.marvelapplication.data.datasource.characterlist.CharacterListRemoteDataSource
import com.ae.marvelapplication.data.datasource.characterlist.CharacterListRemoteDataSourceImpl
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
    ): CharacterListRemoteDataSource = CharacterListRemoteDataSourceImpl(
        characterService
    )
}