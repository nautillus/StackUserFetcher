package com.ipdev.sof.callableapi

import com.ipdev.sof.api.ApiResponse
import com.ipdev.sof.entity.Answer
import com.ipdev.sof.filter.ApiFilter

class AnswerApiCallableWrapper : PathVariableCallableWrapper<ApiResponse<List<Answer>>>() {
    override fun call(filter : ApiFilter) : ApiResponse<List<Answer>>{
        val response = apiManager.service
                                                     .getAnswersByUsers(pathVariable,
                                                                        getCurrentPage().toString(),
                                                                        filter.getFilter()).execute()
        val apiResponse : ApiResponse<List<Answer>> = ApiResponse<List<Answer>>()
        apiResponse.isSuccess = response.isSuccessful
        if(response.isSuccessful) {
            hasMore = if(response.body()?.hasMore != null ) response.body()?.hasMore!! else false
            apiResponse.data = response.body()?.answers!!
        } else {
            apiResponse.error.add(response.errorBody()!!.string())
        }
        return apiResponse
    }
}