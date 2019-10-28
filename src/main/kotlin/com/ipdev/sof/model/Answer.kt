package com.ipdev.sof.entity

import com.squareup.moshi.Json

/**
 * Data model
 */
data class Answer (
    @field:Json(name = "owner")
    var owner: Owner
)