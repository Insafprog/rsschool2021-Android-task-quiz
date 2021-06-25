package com.rsschool.quiz

data class Question(
    val quest: String,
    val option0: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String,
    val answer: Int,
    var thisAnswer: Int? = null
) {
    override fun toString(): String {
        val yourAnswer = when (thisAnswer) {
            0 -> option0
            1 -> option1
            2 -> option2
            3 -> option3
            else -> option4
        }
        return "$quest\nYour answer: $yourAnswer"
    }

    companion object {
        var currentIndex = 0
    }
}
