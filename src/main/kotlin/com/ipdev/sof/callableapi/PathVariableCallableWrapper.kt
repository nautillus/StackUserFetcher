package com.ipdev.sof.callableapi

abstract class PathVariableCallableWrapper<T> : ApiCallableWrapper<T>() {
    var pathVariable : String = ""
}