package com.ndmrzzzv.domain.network.data

data class Lesson(
    val id: Int? = 0,
    val courseId: Int? = 0,
    val description: String? = "",
    val global_question: String? = "",
    val image: String? = "",
    val type: String? = "",
    val questions: List<Question>? = listOf()
)

data class Question(
    val id: Int? = 0,
    val image: String? = "",
    val correct_answer: String? = "",
    val text: String? = ""
)