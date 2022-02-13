package com.ae.data.interceptor

import com.ae.data.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import javax.inject.Inject
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 *  KEYS interceptor
 *
 * Add KEYS to every request
 */
class ApiKeyInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original: Request = chain.request()
        val originalHttpUrl: HttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter(API_KEY, BuildConfig.API_KEY)
            .addQueryParameter(HASH, getHash())
            .addQueryParameter(TS, TS_VALUE)
            .build()

        val requestBuilder: Request.Builder = original.newBuilder()
            .url(url)

        val request: Request = requestBuilder.build()
        return chain.proceed(request)
    }

    private fun getHash(): String {
        val textToHash = "$TS_VALUE${BuildConfig.PRIVATE_KEY}${BuildConfig.API_KEY}"
        val md = MessageDigest.getInstance(MD5)
        return BigInteger(1, md.digest(textToHash.toByteArray()))
            .toString(RADIX).padStart(LENGTH, '0')
    }

    private companion object {
        const val API_KEY: String = "apikey"
        const val TS_VALUE: String = "1"
        const val HASH: String = "hash"
        const val MD5: String = "MD5"
        const val TS: String = "ts"
        const val RADIX: Int = 16
        const val LENGTH: Int = 32
    }
}
