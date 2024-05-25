package com.ndmrzzzv.easygestures.utils

import androidx.compose.ui.graphics.Color
import com.ndmrzzzv.domain.network.data.Lesson

object StudyData {

    var courseId: Int? = null
        get() = field

    var lesson: Lesson? = null
        get() = field

    val questions = listOf(
        ResultOfTest("Show the letter I", "I", "Correct", Color.Green),
        ResultOfTest("Show the letter D", "E", "Incorrect", Color.Red),
        ResultOfTest("Show the letter A", "A", "Correct", Color.Green),
        ResultOfTest("Show the letter F", "F", "Correct", Color.Green),
        ResultOfTest("Show the letter B", "B", "Correct", Color.Green),
        ResultOfTest("Show the letter E", "D", "Incorrect", Color.Red),
        ResultOfTest("Show the letter H", "H", "Correct", Color.Green),
        ResultOfTest("Show the letter G", "G", "Correct", Color.Green),
        ResultOfTest("Show the letter K", "C", "Incorrect", Color.Red),
        ResultOfTest("Show the letter C", "K", "Incorrect", Color.Red),
    )
}

data class ResultOfTest(
    val question: String?,
    val answer: String?,
    val result: String?,
    val colorOfBorder: Color,
)