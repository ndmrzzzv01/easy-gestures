package com.ndmrzzzv.domain.db.repository

import com.ndmrzzzv.domain.db.model.FavouriteCourse

interface CourseDbRepository {

    suspend fun getAllFavouriteCourses(): List<FavouriteCourse>

    suspend fun addFavouriteCourseToDb(course: FavouriteCourse)

    suspend fun deleteFavouriteCourseFromDb(id: String)

}