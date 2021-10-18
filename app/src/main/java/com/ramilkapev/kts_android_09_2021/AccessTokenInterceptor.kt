package com.ramilkapev.kts_android_09_2021

import okhttp3.Interceptor
import okhttp3.Response

class AccessTokenInterceptor : Interceptor {

//    override fun intercept(chain: Interceptor.Chain): Response =
//        if (accessTokenProvider.token?.isNotEmpty() == true) {
//            val token = accessTokenProvider.token
//            val authenticatedRequest = chain.request()
//                .newBuilder()
//                .addHeader("Authorization", "Bearer $token")
//                .build()
//            chain.proceed(authenticatedRequest)
//        } else {
//            val clientId = accessTokenProvider.ACCESS_KEY
//            val authenticatedRequest = chain.request()
//                .newBuilder()
//                .addHeader("Authorization", "Client-ID $clientId")
//                .build()
//            chain.proceed(authenticatedRequest)
//        }

        override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest = request.newBuilder()
            .addHeader("Authorization", "Bearer ${AuthConfig.token.toString()}")
            .build()

        return chain.proceed(newRequest)

    }
}