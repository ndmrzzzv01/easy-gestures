package com.ndmrzzzv.easygestures.ui.screens.tests.write

import androidx.lifecycle.ViewModel
import com.ndmrzzzv.easygestures.ui.screens.tests.data.TestResult
import com.ndmrzzzv.easygestures.utils.StudyData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TestWriteViewModel: ViewModel() {

    private val _userAnswers = MutableStateFlow<List<String>>(emptyList())
    val userAnswers: StateFlow<List<String>> = _userAnswers

    init {
        val questionsSize = StudyData.lesson?.questions?.size ?: 0
        _userAnswers.value = List(questionsSize) { "" }
    }

    fun getLesson() = StudyData.lesson

    fun getQuestionsInLesson() = StudyData.lesson?.questions?.shuffled() ?: listOf()

    fun updateUserAnswer(pageOfQuestion: Int, answer: String) {
        _userAnswers.value = _userAnswers.value.toMutableList().also { it[pageOfQuestion] = answer }
    }

    fun calculateAndSaveResults() {
        val questions = getQuestionsInLesson()
        val result = questions.mapIndexed { index, question ->
            TestResult(
                question = question.text ?: "",
                correctAnswer = question.correct_answer ?: "",
                userAnswer = _userAnswers.value[index]
            )
        }
        StudyData.result = result
    }

}