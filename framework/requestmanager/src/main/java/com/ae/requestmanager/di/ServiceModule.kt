package com.ae.requestmanager.di

import com.ae.data.connectionchecker.CheckConnection
import com.ae.data.connectionchecker.CheckConnectionImpl
import com.ae.data.service.CharacterService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Singleton
    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService =
        retrofit.create(CharacterService::class.java)

    @Singleton
    @Provides
    fun provideCheckConnection(): CheckConnection = CheckConnectionImpl()
}