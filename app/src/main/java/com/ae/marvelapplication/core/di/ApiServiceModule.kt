package com.ae.marvelapplication.core.di


import com.ae.marvelapplication.BuildConfig
import com.ae.marvelapplication.data.interceptor.ApiKeyInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {
    private const val TIME_TIMEOUT: Long = 30

    /**
     * Provide interceptor of http requests
     * @return HttpLoggingInterceptor
     */
    @Provides
    @Singleton
    fun provideNetworkInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    /**
     * Provide interceptor with APIS KEY and Hash
     * @return Interceptor
     */
    @Provides
    @Singleton
    fun provideInterceptor(): Interceptor = ApiKeyInterceptor()

    /**
     * Provide interceptor with APIS KEY and Hash
     * @return Interceptor
     */
    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    /**
     * Provide Http client with behaviors
     * @return OkHttpClient
     */
    @Provides
    @Singleton
    fun provideOkHttpClient(
        networkInterceptor: HttpLoggingInterceptor,
        interceptor: ApiKeyInterceptor,
    ): OkHttpClient {
        val httpBuilder = OkHttpClient.Builder()
            .addNetworkInterceptor(networkInterceptor)
            .connectTimeout(TIME_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(TIME_TIMEOUT, TimeUnit.SECONDS)
            .followRedirects(true)
            .followSslRedirects(true)
            .addInterceptor(interceptor)
        return httpBuilder.build()
    }

    /**
     * Create interface with retrofit generic class
     * @return service to create
     */
    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(httpClient)
        .baseUrl(BuildConfig.BASE_URL)
        .build()

}