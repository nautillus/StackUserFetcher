package com.ipdev.sof.callableapi

import com.ipdev.sof.api.ApiResponse
import com.ipdev.sof.entity.User
import com.ipdev.sof.filter.ApiFilter

class UserApiCallableWrapper : ApiCallableWrapper<ApiResponse<List<User>>>() {
    override fun call(filter : ApiFilter) : ApiResponse<List<User>>{
        val response = apiManager.service
                                                   .getAllUsers(getCurrentPage().toString(), filter.getFilter()).execute()
        val apiResponse : ApiResponse<List<User>> = ApiResponse<List<User>>()
        apiResponse.isSuccess = response.isSuccessful
        if(response.isSuccessful) {
            hasMore = if (response.body()?.hasMore != null) response.body()?.hasMore!! else false

            apiResponse.data = response.body()?.users?.asSequence()
                                            ?.filter { el -> el.location != null }
                                            ?.filter { el -> el.location.contains("Moldova")
                                                          || el.location.contains("Romania") }
                                            ?.filter { el -> el.reputation != null }
                                            ?.filter { el -> el.reputation > 223 }?.toList()!!
        } else {
            apiResponse.error.add(response.errorBody()!!.string())
        }
        return apiResponse
    }
}