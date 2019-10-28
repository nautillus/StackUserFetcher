package com.ipdev.sof.filter
/**
 * Filter applied when call Rest API with user questions endpoint
 */
class QuestionFilter : ApiFilter{
    override fun getFilter(): Map<String, String> {
        return hashMapOf("order" to "desc",
                         "sort" to "creation",
                         "site" to "stackoverflow",
                         "pagesize" to "100",
                         "filter" to "!gB4j)gi2n.neXPiedGgTyWnMhVbgriYNy9_")
    }
}