package com.ipdev.sof.api

import com.ipdev.sof.dto.AnswerDto
import com.ipdev.sof.dto.QuestionDto
import com.ipdev.sof.dto.TagDto
import com.ipdev.sof.dto.UserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

/**
 * Api Rest Client Service
 */
interface ApiServiceInterface {

    @GET("users")
    fun getAllUsers(@Query(value = "page") currentPage: String,
                    @QueryMap() params: Map<String, String>): Call<UserDto>

    @GET("users/{ids}/tags")
    fun getTagsByUsers(@Path("ids") ids: String,
                       @Query(value = "page") currentPage: String,
                       @QueryMap() params: Map<String, String>) : Call<TagDto>

    @GET("users/{ids}/answers")
    fun getAnswersByUsers(@Path("ids") ids : String,
                          @Query(value = "page") currentPage: String,
                          @QueryMap() params: Map<String, String>) : Call<AnswerDto>

    @GET("users/{ids}/questions")
    fun getQuestionsByUsers(@Path("ids") ids : String,
                            @Query(value = "page") currentPage: String,
                            @QueryMap() params: Map<String, String>) : Call<QuestionDto>
}