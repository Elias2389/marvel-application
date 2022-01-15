package com.ae.marvelapplication.core.di

import com.ae.marvelapplication.ui.characterlist.repository.CharacterListRepository
import com.ae.marvelapplication.ui.characterlist.usecase.CharacterListUseCase
import com.ae.marvelapplication.ui.characterlist.usecase.CharacterListUseCaseImpl
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
}