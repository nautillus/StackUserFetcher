package com.ipdev.sof.dto

import com.ipdev.sof.entity.Answer
import com.ipdev.sof.entity.Tag
import com.ipdev.sof.entity.User
import com.squareup.moshi.Json

class AnswerDto {
    @field:Json(name = "items")
    var answers: List<Answer>? = null
    @field:Json(name = "has_more")
    var hasMore: Boolean = false
    @field:Json(name = "total")
    var total: Long = 0L
}