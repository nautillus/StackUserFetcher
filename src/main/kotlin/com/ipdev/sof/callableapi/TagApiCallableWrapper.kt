package com.ipdev.sof.callableapi

import com.ipdev.sof.api.ApiResponse
import com.ipdev.sof.entity.Tag
import com.ipdev.sof.filter.ApiFilter

class TagApiCallableWrapper : PathVariableCallableWrapper<ApiResponse<List<Tag>>>() {
    override fun call(filter : ApiFilter) : ApiResponse<List<Tag>>{
        val response = apiManager.service
                                                  .getTagsByUsers(pathVariable,
                                                                  getCurrentPage().toString(),
                                                                  filter.getFilter()).execute()
        val apiResponse : ApiResponse<List<Tag>> = ApiResponse<List<Tag>>()
        apiResponse.isSuccess = response.isSuccessful
        if(response.isSuccessful) {
            hasMore = if (response.body()?.hasMore != null) response.body()?.hasMore!! else false
            apiResponse.data = response.body()?.tags?.asSequence()
                                                    ?.filter { el -> el.name != null }
                                                    ?.filter { el -> "java".equals(el.name)
                                                                  || ".net".equals(el.name)
                                                                  || "docker".equals(el.name)
                                                                  || "C#".equals(el.name) }?.toList()!!
        } else {
            apiResponse.error.add(response.errorBody()!!.string())
        }
        return apiResponse
    }
}