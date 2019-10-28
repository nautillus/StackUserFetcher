package com.ipdev.sof.entity

import com.squareup.moshi.Json

/**
 * Data model
 */
data class Tag (
    @field:Json(name = "name")
    var name: String,
    @field:Json(name = "count")
    var count: Long,
    @field:Json(name = "user_id")
    var userId: Long
)