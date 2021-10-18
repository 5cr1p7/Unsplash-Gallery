package com.ramilkapev.kts_android_09_2021.networking.models


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Result(
//    @Json(name = "alt_description")
//    val altDescription: String?,
////    @Json(name = "blur_hash")
////    val blurHash: String?,
//    @Json(name = "categories")
//    val categories: List<Any>?,
//    @Json(name = "color")
////    val color: String?,
////    @Json(name = "created_at")
//    val createdAt: String?,
//    @Json(name = "current_user_collections")
//    val currentUserCollections: List<Any>?,
    @Json(name = "description")
    val description: Any?,
//    @Json(name = "height")
//    val height: Int?,
    @Json(name = "id")
    val id: String,
    @Json(name = "liked_by_user")
    var likedByUser: Boolean,
    @Json(name = "likes")
    var likes: Int,
//    @Json(name = "links")
//    val links: Links?,
//    @Json(name = "promoted_at")
//    val promotedAt: Any?,
//    @Json(name = "sponsorship")
//    val sponsorship: Sponsorship?,
//    @Json(name = "tags")
//    val tags: List<Tag>?,
//    @Json(name = "topic_submissions")
//    val topicSubmissions: TopicSubmissionsX?,
//    @Json(name = "updated_at")
//    val updatedAt: String?,
    @Json(name = "urls")
    val urls: Urls,
    @Json(name = "user")
    val user: User,
//    @Json(name = "width")
//    val width: Int?
)