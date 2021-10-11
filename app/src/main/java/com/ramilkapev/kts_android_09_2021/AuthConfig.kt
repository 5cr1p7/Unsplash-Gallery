package com.ramilkapev.kts_android_09_2021

import net.openid.appauth.ResponseTypeValues

object AuthConfig {
    const val AUTH_URI = "https://unsplash.com/oauth/authorize"
    const val TOKEN_URI = "https://unsplash.com/oauth/token"
    const val RESPONSE_TYPE = ResponseTypeValues.CODE
    const val SCOPE =
        "public read_user write_user read_photos write_photos write_likes write_followers read_collections write_collections"

    const val ACCESS_KEY = "5DmFbjgf1Qm4W3x4IY5LvxcO0eLWtJQSEUeY_pm3q5Y"
    const val SECRET_KEY = "NHC2QcSBe1TThBT-paClTYsDR-PybXZHnxux13ZLHD8"
    const val CALLBACK_URL = "insplash://ramil.kapev/callback"

    var token: String? = null
}