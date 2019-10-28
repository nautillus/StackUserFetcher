package com.ipdev.sof.dto

import com.ipdev.sof.entity.User
import com.squareup.moshi.Json

class UserDto {
    @field:Json(name = "items")
    var users: List<User>? = null
    @field:Json(name = "has_more")
    var hasMore: Boolean = false
    @field:Json(name = "total")
    var total: Long = 0L
}