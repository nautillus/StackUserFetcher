package com.ipdev.sof.main.jconfig

import com.ipdev.sof.api.ApiResponse
import com.ipdev.sof.callableapi.*
import com.ipdev.sof.dto.TagDto
import com.ipdev.sof.entity.Answer
import com.ipdev.sof.entity.Question
import com.ipdev.sof.entity.Tag
import com.ipdev.sof.entity.User
import com.ipdev.sof.filter.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BaseConfiguration {

    @Bean("ApiUserFilter")
    fun apiUserFilter () : ApiFilter{
        return UserFilter()
    }

    @Bean("ApiTagFilter")
    fun apiTagFilter () : ApiFilter{
        return TagFilter()
    }

    @Bean("ApiAnswerFilter")
    fun apiAnswerFilter () : ApiFilter{
        return AnswerFilter()
    }

    @Bean("ApiQuestionFilter")
    fun apiQuestionFilter () : ApiFilter{
        return QuestionFilter()
    }

    @Bean("ApiUserCallableWrapper")
    fun userApiCallableWrapper () : ApiCallableWrapper<ApiResponse<List<User>>> {
        return UserApiCallableWrapper()
    }

    @Bean("ApiQuestionCallableWrapper")
    fun questionApiCallableWrapper () : PathVariableCallableWrapper<ApiResponse<List<Question>>> {
        return QuestionApiCallableWrapper()
    }

    @Bean("ApiAnswerCallableWrapper")
    fun answerApiCallableWrapper () : PathVariableCallableWrapper<ApiResponse<List<Answer>>> {
        return AnswerApiCallableWrapper()
    }

    @Bean("ApiTagCallableWrapper")
    fun tagApiCallableWrapper () : PathVariableCallableWrapper<ApiResponse<List<Tag>>> {
        return TagApiCallableWrapper()
    }
}