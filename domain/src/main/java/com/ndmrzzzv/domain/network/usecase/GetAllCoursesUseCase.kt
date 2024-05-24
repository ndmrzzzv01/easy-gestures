package com.ndmrzzzv.domain.network.usecase

import com.ndmrzzzv.domain.network.data.Course
import com.ndmrzzzv.domain.network.repository.CourseNetworkRepository

class GetAllCoursesUseCase(
    private val courseNetworkRepository: CourseNetworkRepository
) {

    suspend operator fun invoke(): List<Course> {
        return courseNetworkRepository.getAllCourses()
    }

}