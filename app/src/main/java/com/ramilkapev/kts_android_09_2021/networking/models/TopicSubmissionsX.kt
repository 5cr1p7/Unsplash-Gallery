package com.ramilkapev.kts_android_09_2021.networking.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TopicSubmissionsX(
    @Json(name = "business-work")
    val businessWork: BusinessWork?,
    @Json(name = "covid-19")
    val covid19: Covid19?,
    @Json(name = "interiors")
    val interiors: Interiors?,
    @Json(name = "wallpapers")
    val wallpapers: Wallpapers?
)