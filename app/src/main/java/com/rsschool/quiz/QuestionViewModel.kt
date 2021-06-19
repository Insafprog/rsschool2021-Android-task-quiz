package com.rsschool.quiz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuestionViewModel: ViewModel() {
    private val questions = Questions.questions.toMutableList()

    private var index: Int = 0

    private var thisQuestion = questions[index]


    fun setIndex(index: Int) {
        this.index = index
        thisQuestion = questions[index]
    }

    fun setQuestion(question: Question) {
        questions[index] = question
        thisQuestion = question
    }

    val getQuestions
        get() = questions

    val getIndex: Int
        get() = index

    val questionsCount: Int
        get() = questions.size

    val question
        get() = thisQuestion

    val result
        get() = questions.filter { it.thisAnswer==it.answer }.count()
}