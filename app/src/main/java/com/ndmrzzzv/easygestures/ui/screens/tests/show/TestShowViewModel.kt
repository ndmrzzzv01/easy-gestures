package com.ndmrzzzv.easygestures.ui.screens.tests.show

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.lifecycle.ViewModel
import com.ndmrzzzv.domain.network.data.Question
import com.ndmrzzzv.easygestures.EasyGesturesApp.Companion.context
import com.ndmrzzzv.easygestures.ui.screens.tests.data.TestResult
import com.ndmrzzzv.easygestures.utils.ClassifyImage
import com.ndmrzzzv.easygestures.utils.StudyData
import com.ndmrzzzv.network.utils.createImageFile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TestShowViewModel(
    private val classifyImage: ClassifyImage,
): ViewModel() {

    private var _shuffledQuestions = MutableStateFlow<List<Question>>(emptyList())
    val shuffledQuestions: StateFlow<List<Question>> = _shuffledQuestions

    init {
        shuffleQuestions()
    }

    private var _currentQuestion = MutableStateFlow<String?>(null)
    val currentQuestion: StateFlow<String?> = _currentQuestion

    private var _currentUri =  MutableStateFlow<Uri?>(null)
    val currentUri: StateFlow<Uri?> = _currentUri

    private val results = mutableStateListOf<TestResult>()
    val imageUriMap = mutableStateMapOf<String, Uri>()

    fun updateCurrentQuestion(question: String?) {
        _currentQuestion.value = question
    }

    fun updateCurrentUri(uri: Uri?) {
        _currentUri.value = uri
    }

    fun saveImageUri(question: String, uri: Uri) {
        imageUriMap[question] = uri
    }

    fun createImageUri(): Uri {
        val file = context.createImageFile()
        return FileProvider.getUriForFile(
            context, "com.ndmrzzzv.easygestures.provider", file
        )
    }

    fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context, Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun classifyImageForAll(
        onComplete: () -> Unit
    ) {
        imageUriMap.forEach { (questionText, uri) ->
            classifyImage.classifyImage(uri) { userAnswer ->
                val correctAnswer = getCorrectAnswer(questionText)

                results.add(
                    TestResult(
                        question = questionText,
                        correctAnswer = correctAnswer,
                        userAnswer = userAnswer
                    )
                )

                if (results.size == imageUriMap.size) {
                    StudyData.result = results.toList()
                    onComplete()
                }
            }
        }
    }

    private fun getCorrectAnswer(questionText: String): String {
        return questionText.last().toString()
    }

    fun getLesson() = StudyData.lesson

    private fun shuffleQuestions() {
        _shuffledQuestions.value = StudyData.lesson?.questions?.shuffled() ?: listOf()
    }
}