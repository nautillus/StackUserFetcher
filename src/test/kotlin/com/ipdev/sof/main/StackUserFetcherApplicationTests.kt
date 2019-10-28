package com.ipdev.sof.main

import com.ipdev.sof.entity.*
import com.ipdev.sof.main.helper.ApplicationHelper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class StackUserFetcherApplicationTests {
	@Autowired
	lateinit var ah : ApplicationHelper

	@Test
	fun contextLoads() {

	}

	@Test
	fun testGetUsersIds() {
		var users : MutableList<User> = ArrayList<User>()
		for (i in 1..110) {
			users.add(User(i.toLong(), "", (i + 50).toLong(),
					"", i + 3, i + 2,
					   "", "", ""))
		}
		val userIds = ah.getUserIds(users)
		assert(userIds.size == 2, { "Method getUsersIds failed, expected list size 2 but was ${userIds.size}" })
	}

	@Test
	fun testGetUsersIdsEmptyList() {
		var users : List<User> = ArrayList<User> ()
		val userIds = ah.getUserIds(users)
		assert(userIds.size == 0, { "Method getUsersIds failed, expected list size 0 but was ${userIds.size}" })
	}

	@Test
	fun testnormalizeUsersEmptyList() {
		var questions : MutableList<Question> = ArrayList<Question> ()
		var answers : MutableList<Answer> = ArrayList<Answer> ()
		var tags : MutableList<Tag> = ArrayList<Tag> ()
		var users : MutableList<User> = ArrayList<User> ()
		val nomalizedUsers = ah.normalizeUsers(questions, answers, tags, users)
		assert(nomalizedUsers.size == 0,
				{ "Method normalizeUsers failed, expected list size 0 but was ${nomalizedUsers.size}" })
	}

	@Test
	fun testnormalizeUsers() {
		var questions : MutableList<Question> = ArrayList<Question> ()
		var answers : MutableList<Answer> = ArrayList<Answer> ()
		var tags : MutableList<Tag> = ArrayList<Tag> ()
		var users : MutableList<User> = ArrayList<User> ()
		// populate our collections
		for(i in 1..1_001){
			users.add(User(i.toLong(), "", (i+2).toLong(),
					"", i+3, i+2,
					"", "", ""))
			tags.add(Tag("C#", (i+1).toLong(), i.toLong()))
			answers.add(Answer(Owner(i.toLong())))
			questions.add(Question(Owner(i.toLong())))
		}
		answers.add(Answer(Owner(1)))
		questions.add(Question(Owner(1)))
		tags.add(Tag("java", 2, 1))
		tags.add(Tag("java", 6, 1))
		val nomalizedUsers = ah.normalizeUsers(questions, answers, tags, users)
		assert(nomalizedUsers.size == 1001, { "Method normalizeUsers failed, expected list size 0 but was ${nomalizedUsers.size}" })
		assert(nomalizedUsers.get(0).answerCounter == 2,
				{ "Method normalizeUsers failed, expected answerCounter to be 2 but was ${nomalizedUsers.get(0).answerCounter}" })
		assert(nomalizedUsers.get(0).questionCounter == 2,
				{ "Method normalizeUsers failed, expected questionCounter to be 2 but was ${nomalizedUsers.get(0).questionCounter}" })
		assert(nomalizedUsers.get(0).tags.equals("C#;java"),
				{ "Method normalizeUsers failed, expected tags to be \"C#;java\" but was ${nomalizedUsers.get(0).tags}" })
	}

	@Test
	fun testfilterUsersByAnswersCounterEmptyList() {
		var users : MutableList<User> = ArrayList<User> ()
		val filteredUsers = ah.filterUsersByAnswersCounter(users)
		assert(filteredUsers.size == 0,
				{ "Method filterUsersByAnswersCounter failed, expected list size 0 but was ${filteredUsers.size}" })
	}

	@Test
	fun testfilterUsersByAnswers() {
		var users : MutableList<User> = ArrayList<User> ()
		for(i in 0..11){
			users.add(User(i.toLong(), "RickandMorti", (i+2).toLong(),
					"Moldova", i, i+4,
					"java", "http", "https"))
		}
		val filteredUsers = ah.filterUsersByAnswersCounter(users)
		assert(filteredUsers.size == 11,
				{ "Method filterUsersByAnswersCounter failed, expected list size 11 but was ${filteredUsers.size}" })
	}
}
