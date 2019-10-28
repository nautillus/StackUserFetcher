package com.ipdev.sof.callableapi

interface Paginate {
    fun getCurrentPage() : Int
    fun getPreviousPage() : Int
    fun getNextPage() : Int
}