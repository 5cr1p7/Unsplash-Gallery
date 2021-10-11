package com.ramilkapev.kts_android_09_2021.networking.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Type(
    @Json(name = "pretty_slug")
    val prettySlug: String?,
    @Json(name = "slug")
    val slug: String?
)