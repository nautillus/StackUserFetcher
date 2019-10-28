package com.ipdev.sof.main.helper

import com.ipdev.sof.entity.Answer
import com.ipdev.sof.entity.Question
import com.ipdev.sof.entity.Tag
import com.ipdev.sof.entity.User
import org.springframework.stereotype.Component

@Component
class ApplicationHelper {
    fun getUserIds(users: List<User>) : List<String> {
        var list : MutableList<String> = ArrayList<String>()
        if(users.size > 0) {
            val chunked: List<List<User>> = users.chunked(100)
            for (chunk in chunked){
                val joinToString = chunk.joinToString(separator = ";") { it.userId.toString() }
                list.add(joinToString)
            }

        }
        return list
    }

    fun normalizeUsers(questions: MutableList<Question>,
                       answers: MutableList<Answer>,
                       tags: MutableList<Tag>,
                       users: MutableList<User>) : MutableList<User> {
        var list : MutableList<User> = ArrayList(users)
        val totalQuestionsEachUser: Map<Long, Int> = questions.groupingBy { it.owner.userId }.eachCount()
        val totalAnswersEachUser: Map<Long, Int> = answers.groupingBy { it.owner.userId }.eachCount()
        val totalTagsEachUser: Map<Long, List<Tag>> = tags.groupBy { it.userId }

        // set questions/answers/tags counters for all users
        for (user in list) {
            user.questionCounter = totalQuestionsEachUser.getOrDefault(user.userId, 0)!!
            user.answerCounter = totalAnswersEachUser.getOrDefault(user.userId, 0)!!
            user.tags = totalTagsEachUser.getOrDefault(user.userId, ArrayList())
                    .groupBy { it.name }.keys.joinToString (separator = ";")
        }
        return list
    }

    fun filterUsersByAnswersCounter(users: MutableList<User>): List<User> {
        return users.filter { el -> el.answerCounter > 0 }
    }
}