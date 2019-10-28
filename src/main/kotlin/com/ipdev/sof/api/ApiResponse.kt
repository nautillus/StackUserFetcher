package com.ipdev.sof.api

/**
 * Rest API response
 */
class ApiResponse<T> {
    var data : T? = null
    var isSuccess : Boolean = false
    var error : MutableList<String> = ArrayList()
}