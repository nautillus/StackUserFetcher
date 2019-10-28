package com.ipdev.sof.filter
/**
 * Filter applied when call Rest API with user tags endpoint
 */
class TagFilter : ApiFilter{
    override fun getFilter(): Map<String, String> {
        return hashMapOf("order" to "desc",
                         "sort" to "name",
                         "site" to "stackoverflow",
                         "pagesize" to "100",
                         "filter" to "!*MM6ia7urblIdW7x")
    }
}