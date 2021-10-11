package com.ramilkapev.kts_android_09_2021.networking.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Ancestry(
    @Json(name = "category")
    val category: Category?,
    @Json(name = "subcategory")
    val subcategory: Subcategory?,
    @Json(name = "type")
    val type: Type?
)