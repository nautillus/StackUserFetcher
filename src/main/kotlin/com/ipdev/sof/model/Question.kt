package com.ipdev.sof.entity

import com.squareup.moshi.Json

/**
 * Data model
 */
data class Question (
    @field:Json(name = "owner")
    var owner: Owner
)