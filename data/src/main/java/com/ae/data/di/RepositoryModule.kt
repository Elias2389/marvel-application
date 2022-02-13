package com.ae.data.di

import com.ae.data.connectionchecker.CheckConnection
import com.ae.data.datasource.CharacterLocalDataSource
import com.ae.data.datasource.CharactersRemoteDataSource
import com.ae.data.repository.characterdetail.CharacterDetailRepository
import com.ae.data.repository.characterdetail.CharacterDetailRepositoryImpl
import com.ae.data.repository.characterlist.CharacterListRepository
import com.ae.data.repository.characterlist.CharacterListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideCharacterListRepository(
        remote: CharactersRemoteDataSource,
        local: CharacterLocalDataSource,
        checkConnect: CheckConnection
    ): CharacterListRepository = CharacterListRepositoryImpl(remote, local, checkConnect)

    @Singleton
    @Provides
    fun provideCharacterDetailRepository(
        remote: CharactersRemoteDataSource
    ): CharacterDetailRepository = CharacterDetailRepositoryImpl(remote)
}