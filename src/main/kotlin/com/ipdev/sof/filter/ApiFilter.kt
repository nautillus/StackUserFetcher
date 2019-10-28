package com.ipdev.sof.filter

interface ApiFilter {
    fun getFilter(): Map<String, String>
}