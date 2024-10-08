package com.ndmrzzzv.easygestures.utils

import com.ndmrzzzv.domain.network.data.Lesson
import com.ndmrzzzv.easygestures.ui.screens.tests.data.TestResult

object StudyData {

    var courseId: Int? = null
        get() = field

    var lesson: Lesson? = null
        get() = field

    var result = listOf<TestResult>()
}