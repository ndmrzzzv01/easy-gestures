package com.ndmrzzzv.domain.network.repository

import com.ndmrzzzv.domain.network.data.Course

interface CourseNetworkRepository {

    suspend fun getAllCourses(): List<Course>

}