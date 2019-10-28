package com.ipdev.sof.main.service

import com.ipdev.sof.api.ApiServiceInterface
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.stereotype.Service
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Create single thread safe instance for rest api client
 */
class ApiManager {

    val service : ApiServiceInterface by lazy {
        var sofConfig: ApiConfig = ApiConfig()
        val retrofit = Retrofit.Builder()
            .baseUrl(sofConfig.baseUrl + sofConfig.version + sofConfig.defUrlSep)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
        return@lazy retrofit.create(ApiServiceInterface::class.java)
    }
}