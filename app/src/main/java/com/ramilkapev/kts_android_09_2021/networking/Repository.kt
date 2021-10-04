package com.ramilkapev.kts_android_09_2021.networking

import com.ramilkapev.kts_android_09_2021.networking.models.Result
import com.ramilkapev.kts_android_09_2021.networking.models.User
import com.ramilkapev.kts_android_09_2021.networking.Network

class Repository {

    suspend fun searchUsers(
        query: String,
    ): List<User>? {
        return Network.unsplashApi.searchUsers(query, "5DmFbjgf1Qm4W3x4IY5LvxcO0eLWtJQSEUeY_pm3q5Y").results
    }

    suspend fun searchPhotos(
        query: String,
        page: Int
    ): List<Result>? {
        return Network.unsplashApi.searchPhotos(query, page, "5DmFbjgf1Qm4W3x4IY5LvxcO0eLWtJQSEUeY_pm3q5Y").results
    }
}