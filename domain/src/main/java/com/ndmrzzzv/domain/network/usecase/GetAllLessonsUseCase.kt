package com.ndmrzzzv.domain.network.usecase

import com.ndmrzzzv.domain.network.data.Lesson
import com.ndmrzzzv.domain.network.repository.CourseNetworkRepository

class GetAllLessonsUseCase(
    private val courseNetworkRepository: CourseNetworkRepository
) {

    suspend operator fun invoke(courseId: Int): List<Lesson> {
        return courseNetworkRepository.getAllLessonsByCourseId(courseId)
    }

}