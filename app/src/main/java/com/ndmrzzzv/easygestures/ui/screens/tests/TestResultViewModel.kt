package com.ndmrzzzv.easygestures.ui.screens.tests

import androidx.lifecycle.ViewModel
import com.ndmrzzzv.easygestures.utils.StudyData

class TestResultViewModel: ViewModel() {

    fun getLesson() = StudyData.lesson

    fun getQuestionsInLesson() = StudyData.lesson?.questions?.shuffled() ?: listOf()

}