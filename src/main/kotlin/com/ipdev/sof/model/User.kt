package com.ipdev.sof.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * Data model
 */
data class User(
    @field:Json(name = "user_id")
    var userId: Long,
    @field:Json(name = "display_name")
    var name: String,
    @field:Json(name = "reputation")
    var reputation: Long,
    @field:Json(name = "location")
    var location: String,
    var answerCounter: Int,
    var questionCounter: Int,
    var tags: String,
    @field:Json(name = "link")
    var profileLink: String,
    @field:Json(name = "profile_image")
    var avatarLink: String
)