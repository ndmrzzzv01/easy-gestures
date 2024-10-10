package com.ndmrzzzv.easygestures.ui.screens.tests.show

import androidx.lifecycle.ViewModel
import com.ndmrzzzv.easygestures.ui.screens.tests.data.TestResult
import com.ndmrzzzv.easygestures.utils.StudyData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TestShowViewModel: ViewModel() {

    fun getLesson() = StudyData.lesson

}