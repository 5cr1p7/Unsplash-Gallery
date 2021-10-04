package com.ramilkapev.kts_android_09_2021.networking.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopicSubmissions(
    @Json(name = "current-events")
    val currentEvents: CurrentEvents?,
    @Json(name = "textures-patterns")
    val texturesPatterns: TexturesPatterns?
)