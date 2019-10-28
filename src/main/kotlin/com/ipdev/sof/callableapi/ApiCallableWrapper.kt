package com.ipdev.sof.callableapi

import com.ipdev.sof.filter.ApiFilter
import com.ipdev.sof.main.service.ApiManager
import java.util.concurrent.atomic.AtomicInteger

abstract class ApiCallableWrapper<T> : Paginate {
    var page : AtomicInteger = AtomicInteger(1)
    @Volatile var hasMore : Boolean = false
    val apiManager : ApiManager = ApiManager()

    abstract fun call(filter : ApiFilter) : T

    override fun getCurrentPage(): Int {
        return page.get();
    }

    override fun getPreviousPage(): Int {
        return page.decrementAndGet()
    }

    override fun getNextPage(): Int {
        return page.incrementAndGet()
    }
}