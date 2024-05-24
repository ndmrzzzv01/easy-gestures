package com.ndmrzzzv.domain.network.repository

import com.ndmrzzzv.domain.network.data.Course
import com.ndmrzzzv.domain.network.data.Lesson

interface CourseNetworkRepository {

    suspend fun getAllCourses(): List<Course>

    suspend fun getAllLessonsByCourseId(courseId: Int): List<Lesson>

}