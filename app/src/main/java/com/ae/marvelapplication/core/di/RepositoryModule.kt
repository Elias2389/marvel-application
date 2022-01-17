package com.ae.marvelapplication.core.di

import com.ae.marvelapplication.common.connectionchecker.CheckConnection
import com.ae.marvelapplication.data.datasource.character.CharacterLocalDataSource
import com.ae.marvelapplication.data.datasource.character.CharactersRemoteDataSource
import com.ae.marvelapplication.ui.characterdetail.repository.CharacterDetailRepository
import com.ae.marvelapplication.ui.characterdetail.repository.CharacterDetailRepositoryImpl
import com.ae.marvelapplication.ui.characterlist.repository.CharacterListRepository
import com.ae.marvelapplication.ui.characterlist.repository.CharacterListRepositoryImpl
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