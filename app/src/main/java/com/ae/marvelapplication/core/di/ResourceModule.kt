package com.ae.marvelapplication.core.di

import android.content.Context
import com.ae.marvelapplication.common.reponse.ResponseHandler
import com.ae.marvelapplication.common.reponse.ResponseHandlerImpl
import com.ae.marvelapplication.common.resource.ResourceProvider
import com.ae.marvelapplication.common.resource.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ResourceModule {

    @Singleton
    @Provides
    fun provideResourceProvider(@ApplicationContext context: Context): ResourceProvider {
        return ResourceProviderImpl(context)
    }

    @Singleton
    @Provides
    fun provideResponseHandler(resourceProvider: ResourceProvider): ResponseHandler {
        return ResponseHandlerImpl(resourceProvider)
    }
}