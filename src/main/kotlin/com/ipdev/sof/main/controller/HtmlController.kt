package com.ipdev.sof.main.controller

import com.ipdev.sof.api.ApiResponse
import com.ipdev.sof.callableapi.ApiCallableWrapper
import com.ipdev.sof.callableapi.PathVariableCallableWrapper
import com.ipdev.sof.entity.Answer
import com.ipdev.sof.entity.Question
import com.ipdev.sof.entity.Tag
import com.ipdev.sof.entity.User
import com.ipdev.sof.filter.ApiFilter
import com.ipdev.sof.main.helper.ApplicationHelper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

/**
 * Main controller
 */
@Controller
class HtmlController {
    @Autowired
    @Qualifier(value = "ApiUserFilter")
    lateinit var userFilter: ApiFilter
    @Autowired
    @Qualifier(value = "ApiTagFilter")
    lateinit var tagFilter: ApiFilter
    @Autowired
    @Qualifier(value = "ApiAnswerFilter")
    lateinit var answerFilter: ApiFilter
    @Autowired
    @Qualifier(value = "ApiQuestionFilter")
    lateinit var questionFilter: ApiFilter

    @Autowired
    @Qualifier(value = "ApiUserCallableWrapper")
    lateinit var uacw: ApiCallableWrapper<ApiResponse<List<User>>>

    @Autowired
    @Qualifier(value = "ApiQuestionCallableWrapper")
    lateinit var qacw: PathVariableCallableWrapper<ApiResponse<List<Question>>>

    @Autowired
    @Qualifier(value = "ApiAnswerCallableWrapper")
    lateinit var aacw: PathVariableCallableWrapper<ApiResponse<List<Answer>>>

    @Autowired
    @Qualifier(value = "ApiTagCallableWrapper")
    lateinit var tacw: PathVariableCallableWrapper<ApiResponse<List<Tag>>>

    @Autowired
    lateinit var applicationHelper: ApplicationHelper

    var errors: MutableList<String> = ArrayList()

    @GetMapping("/")
    fun htmlIndex(model: Model): String {
        return "index"
    }

    @GetMapping("/pages/{count}")
    fun htmlUsers(@PathVariable(value = "count") count: Int, model: Model): String {
        // clear error collection on every page reload
        errors.clear()

        var users: MutableList<User> = ArrayList()
        var questions: MutableList<Question> = ArrayList()
        var answers: MutableList<Answer> = ArrayList()
        var tags: MutableList<Tag> = ArrayList()

        //default pages when fetch users from rest API
        var pagesCounter = 2

        if (count > 1)
            pagesCounter = count

        // reset all pages to first page every time a page gets reloaded
        uacw.page = AtomicInteger(1)
        qacw.page = AtomicInteger(1)
        aacw.page = AtomicInteger(1)
        tacw.page = AtomicInteger(1)

        // fetch all users based on pages counter
        users = fetchUsers(pagesCounter)

        val userIds = applicationHelper.getUserIds(users)

        // fetch all users' attributes
        for (uids in userIds) {
            questions.addAll(fetchUserAttributes(qacw, questionFilter, uids))
            answers.addAll(fetchUserAttributes(aacw, answerFilter, uids))
            tags.addAll(fetchUserAttributes(tacw, tagFilter, uids))
        }

        users = applicationHelper.normalizeUsers(questions, answers, tags, users)

        model["users"] = applicationHelper.filterUsersByAnswersCounter(users)
        model["errors"] = errors
        return "user"
    }

    /**
     * @param pvcw - Callable Wrapper for Rest API with path variable parameter
     * @param filter - Rest API Filter to get specific users attributes
     * @param pv - Path variable for Rest API call
     * @return List of users' attributes
     */
    fun <T, V> fetchUserAttributes(pvcw: PathVariableCallableWrapper<out ApiResponse<V>>, filter: ApiFilter, pv: String): List<T> {

        var list: MutableList<T> = ArrayList<T>()
        // set values in path variable for Rest API request
        pvcw.pathVariable = pv
        do {
            val call = pvcw.call(filter)
            // in case of unsuccessful response
            if (!call.isSuccess) {
                errors.addAll(call.error)
            }
            list.addAll(call.data as Collection<T>)
            if (pvcw.hasMore)
                pvcw.getNextPage()

            Thread.sleep(100) // small delay between api calls to prevent stackoverflow throttling policy
        } while (pvcw.hasMore)
        return list
    }

    /**
     * @param pagesCounter - specify how much pages must be fetched from Rest API Server (one page contains 100 items by default)
     * @return List of users which match specific criteria from @ApiUserFilter
     */
    private fun fetchUsers(pagesCounter: Int): MutableList<User> {
        var list: MutableList<User> = ArrayList()
        for (i in 1..pagesCounter) {
            val call = uacw.call(userFilter)
            // in case of unsuccessful response
            if (!call.isSuccess) {
                errors.addAll(call.error)
            }
            call.data?.let { list.addAll(it) }
            if (uacw.hasMore)
                uacw.getNextPage()
            else
                break //in case if no more items exit the loop
        }
        return list;
    }
}