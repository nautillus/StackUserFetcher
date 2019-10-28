package com.ipdev.sof.callableapi

import com.ipdev.sof.api.ApiResponse
import com.ipdev.sof.entity.Question
import com.ipdev.sof.filter.ApiFilter

class QuestionApiCallableWrapper : PathVariableCallableWrapper<ApiResponse<List<Question>>>() {
    override fun call(filter : ApiFilter) : ApiResponse<List<Question>>{
        val response = apiManager.service
                                                      .getQuestionsByUsers(pathVariable,
                                                                           getCurrentPage().toString(),
                                                                           filter.getFilter()).execute()
        val apiResponse : ApiResponse<List<Question>> = ApiResponse<List<Question>>()
        apiResponse.isSuccess = response.isSuccessful
        if(response.isSuccessful) {
            hasMore = if(response.body()?.hasMore != null ) response.body()?.hasMore!! else false
            apiResponse.data = response.body()?.questions!!
        } else {
            apiResponse.error.add(response.errorBody()!!.string())
        }
        return apiResponse
    }
}