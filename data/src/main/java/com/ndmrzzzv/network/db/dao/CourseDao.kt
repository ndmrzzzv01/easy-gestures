package com.ndmrzzzv.network.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.ndmrzzzv.network.db.data.FavouriteCourse

@Dao
interface CourseDao {

    @Query("SELECT * FROM favourite_course")
    suspend fun getAllFavouriteCourses(): List<FavouriteCourse>

    @Insert
    suspend fun addFavouriteCourseToDb(course: FavouriteCourse)

    @Query("DELETE from favourite_course WHERE id=(:id)")
    suspend fun deleteFavouriteCourseFromDb(id: String)

}