package com.ae.usecase.di

import com.ae.data.repository.characterdetail.CharacterDetailRepository
import com.ae.data.repository.characterlist.CharacterListRepository
import com.ae.usecase.CharacterDetailUserCase
import com.ae.usecase.CharacterDetailUserCaseImpl
import com.ae.usecase.CharacterListUseCase
import com.ae.usecase.CharacterListUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideCharacterListUseCase(
        repository: CharacterListRepository
    ): CharacterListUseCase = CharacterListUseCaseImpl(repository)

    @Singleton
    @Provides
    fun provideCharacterDetailUserCase(
        repository: CharacterDetailRepository
    ): CharacterDetailUserCase = CharacterDetailUserCaseImpl(repository)
}