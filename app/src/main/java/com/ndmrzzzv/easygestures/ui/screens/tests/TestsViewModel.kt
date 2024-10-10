package com.ndmrzzzv.easygestures.ui.screens.tests

import androidx.lifecycle.ViewModel
import com.ndmrzzzv.easygestures.utils.StudyData

class TestsViewModel: ViewModel() {

    fun getLesson() = StudyData.lesson

}