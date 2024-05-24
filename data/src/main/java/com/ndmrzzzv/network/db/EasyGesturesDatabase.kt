package com.ndmrzzzv.network.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ndmrzzzv.network.db.dao.CourseDao
import com.ndmrzzzv.network.db.data.FavouriteCourse

@Database(entities = [FavouriteCourse::class], version = 1)
abstract class EasyGesturesDatabase : RoomDatabase() {

    abstract fun getCourseDao(): CourseDao

}