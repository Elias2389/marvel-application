package com.ae.marvelapplication.core.di

import com.ae.marvelapplication.common.connectionchecker.CheckConnection
import com.ae.marvelapplication.common.connectionchecker.CheckConnectionImpl
import com.ae.marvelapplication.data.service.CharacterService
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