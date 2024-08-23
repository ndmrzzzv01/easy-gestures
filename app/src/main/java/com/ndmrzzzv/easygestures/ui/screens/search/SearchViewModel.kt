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
    private var allCourses = listOf<Course>()

    private val _searchText = MutableStateFlow("")

    init {
        getAllCourses()
    }

    private fun getAllCourses() {
        viewModelScope.launch {
            val result = CourseState.Success(getAllCoursesUseCase())
            _courses.value = result
            allCourses = result.courses
        }
    }

    fun setSearchText(searchText: String) {
        _searchText.value = searchText
        filterCourses()
    }

    private fun filterCourses() {
        val searchResult = allCourses.filter {
            it.title?.lowercase()?.contains(_searchText.value.lowercase()) == true
        }
        _courses.value = CourseState.Success(searchResult)
    }

}

sealed class CourseState {
    object Loading : CourseState()
    data class Success(val courses: List<Course>) : CourseState()
    data class Error(val message: String) : CourseState()
}