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
)
