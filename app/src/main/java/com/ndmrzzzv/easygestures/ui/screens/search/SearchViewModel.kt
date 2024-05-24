package com.ndmrzzzv.easygestures.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ndmrzzzv.domain.network.data.Course
import com.ndmrzzzv.domain.network.usecase.GetAllCoursesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(
    private val getAllCoursesUseCase: GetAllCoursesUseCase
): ViewModel() {

    private val _courses = MutableStateFlow<CourseState>(CourseState.Loading)
    val courses: StateFlow<CourseState> = _courses

    init {
        getAllCourses()
    }

    private fun getAllCourses() {
        viewModelScope.launch {
            _courses.value = CourseState.Success(getAllCoursesUseCase())
        }
    }

}

sealed class CourseState {
    object Loading : CourseState()
    data class Success(val courses: List<Course>) : CourseState()
    data class Error(val message: String) : CourseState()
}