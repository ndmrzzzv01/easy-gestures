package com.ndmrzzzv.network.db.repository

import com.ndmrzzzv.domain.db.model.FavouriteCourse
import com.ndmrzzzv.domain.db.repository.CourseDbRepository
import com.ndmrzzzv.network.db.dao.CourseDao

class CourseDbRepositoryImpl(
    private val courseDao: CourseDao
): CourseDbRepository {

    override suspend fun getAllFavouriteCourses(): List<FavouriteCourse> {
        return courseDao.getAllFavouriteCourses().map {
            FavouriteCourse(it.id, it.userId, it.courseId)
        }
    }

    override suspend fun addFavouriteCourseToDb(course: FavouriteCourse) {
        val courseDb = com.ndmrzzzv.network.db.data.FavouriteCourse(course.id, course.userId, course.courseId)
        courseDao.addFavouriteCourseToDb(courseDb)
    }

    override suspend fun deleteFavouriteCourseFromDb(id: String) {
        courseDao.deleteFavouriteCourseFromDb(id)
    }

}