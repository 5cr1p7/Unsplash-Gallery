package com.ramilkapev.kts_android_09_2021.networking

import com.ramilkapev.kts_android_09_2021.networking.models.Result
import com.ramilkapev.kts_android_09_2021.networking.models.SearchImage
import com.ramilkapev.kts_android_09_2021.networking.models.User
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UnsplashApi {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("query") query: String,
        @Query("client_id") client_id: String
    ): SearchImage<User>

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("client_id") client_id: String
    ): SearchImage<Result>

    @POST("/photos/:id/like")
    suspend fun likePhoto(
        @Query("id") photoId: String,
        @Query("client_id") client_id: String
    )
}