package com.ramilkapev.kts_android_09_2021.networking.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Covid19(
    @Json(name = "approved_on")
    val approvedOn: String?,
    @Json(name = "status")
    val status: String?
)