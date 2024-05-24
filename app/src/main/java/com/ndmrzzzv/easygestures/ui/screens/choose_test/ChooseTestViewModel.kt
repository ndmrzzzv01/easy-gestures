package com.ndmrzzzv.easygestures.ui.screens.choose_test

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndmrzzzv.domain.network.data.Lesson
import com.ndmrzzzv.domain.network.usecase.GetAllLessonsUseCase
import com.ndmrzzzv.easygestures.utils.StudyData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChooseTestViewModel(
    private val getAllLessonsUseCase: GetAllLessonsUseCase
) : ViewModel() {

    private val _lessons = MutableStateFlow<LessonState>(LessonState.Loading)
    val lessons: StateFlow<LessonState> = _lessons

    init {
        getAllCourses()
    }

    private fun getAllCourses() {
        viewModelScope.launch {
            val result = getAllLessonsUseCase(StudyData.courseId ?: 0)
            _lessons.value =
                if (result.isEmpty()) LessonState.None
                else LessonState.Success(result)
        }
    }

}

sealed class LessonState {
    object Loading : LessonState()
    object None : LessonState()
    data class Success(val lessons: List<Lesson>) : LessonState()
    data class Error(val message: String) : LessonState()
}