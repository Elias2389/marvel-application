package com.ae.marvelapplication.core.di

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

}