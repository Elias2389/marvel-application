package com.ae.marvelapplication.core.di

import com.ae.marvelapplication.data.datasource.characterlist.CharacterListRemoteDataSource
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
        remote: CharacterListRemoteDataSource
    ): CharacterListRepository = CharacterListRepositoryImpl(remote)

}