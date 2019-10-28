package com.ipdev.sof.entity

import com.squareup.moshi.Json

/**
 * Data model
 */
data class Owner (
        @field:Json(name = "user_id")
        var userId: Long
)