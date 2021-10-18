package com.ramilkapev.kts_android_09_2021.networking

import com.ramilkapev.kts_android_09_2021.networking.models.Result
import com.ramilkapev.kts_android_09_2021.networking.models.SearchImage
import com.ramilkapev.kts_android_09_2021.networking.models.User
import retrofit2.http.*

interface UnsplashApi {
    @GET("search/users")
    suspend fun searchUsers(
        @Query("query") query: String,
        @Query("client_id") client_id: String
    ): SearchImage<User>

    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
        @Query("page") page: Int = 1
    ): SearchImage<Result>

    @GET("photos/{id}")
    suspend fun getPhotoById(@Path("id") id: String): Result

    @POST("/photos/{id}/like")
    suspend fun likePhoto(
        @Path("id") photoId: String
    ): Result

    @DELETE("/photos/{id}/like")
    suspend fun unlikePhoto(
        @Path("id") photoId: String
    ): Result
}