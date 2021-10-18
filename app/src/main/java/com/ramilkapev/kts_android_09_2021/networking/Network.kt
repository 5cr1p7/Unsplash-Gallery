package com.ramilkapev.kts_android_09_2021.networking

import com.ramilkapev.kts_android_09_2021.AccessTokenInterceptor
import com.ramilkapev.kts_android_09_2021.AuthConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import timber.log.Timber

object Network {

    private val okhttpClient = OkHttpClient.Builder()
        .addNetworkInterceptor(
            HttpLoggingInterceptor {
                Timber.tag("Network").d(it)
            }
                .setLevel(HttpLoggingInterceptor.Level.BODY)
        )
        .addInterceptor(AccessTokenInterceptor())
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.unsplash.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .client(okhttpClient)
        .build()

    val unsplashApi: UnsplashApi
        get() = retrofit.create()
}