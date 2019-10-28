package com.ipdev.sof.filter

/**
 * Filter applied when call Rest API with user answers endpoint
 */
class AnswerFilter : ApiFilter{
    override fun getFilter(): Map<String, String> {
        return hashMapOf("order" to "desc",
                         "sort" to "activity",
                         "site" to "stackoverflow",
                         "pagesize" to "100",
                         "filter" to "!FcbKgRCFrag*C_zEPxUmdFhxc)")
    }
}