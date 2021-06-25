package com.rsschool.quiz

import androidx.lifecycle.ViewModel

class QuestionViewModel: ViewModel() {
    private val questions = Questions.questions.toMutableList()

    private var index: Int = Question.currentIndex

    private var thisQuestion = questions[index]


    fun setIndex(index: Int) {
        this.index = index
        Question.currentIndex = index
        thisQuestion = questions[index]
    }

    fun setQuestion(question: Question) {
        questions[index] = question
        thisQuestion = question
    }

    val getIndex: Int
        get() = index

    val questionsCount: Int
        get() = questions.size

    val question
        get() = thisQuestion

    val result
        get() = questions.filter { it.thisAnswer==it.answer }.count()

    override fun toString(): String {
        var result = "Your result: $result out of $questionsCount"
        for ((i, quest) in questions.withIndex()) {
            result += "\n\n${i + 1}) $quest"
        }
        return result
    }
}