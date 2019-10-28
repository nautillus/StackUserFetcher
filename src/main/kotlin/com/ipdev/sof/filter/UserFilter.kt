package com.ipdev.sof.filter
/**
 * Filter applied when call Rest API with users endpoint
 */
class UserFilter : ApiFilter{
    override fun getFilter(): Map<String, String> {
        return hashMapOf("order" to "desc",
                         "sort" to "reputation",
                         "site" to "stackoverflow",
                         "pagesize" to "100",
                         "filter" to "!G*klMr_b-laJQS*owE3McTMtN)")
    }
}