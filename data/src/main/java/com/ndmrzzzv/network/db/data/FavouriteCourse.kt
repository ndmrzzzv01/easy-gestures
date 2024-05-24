package com.ndmrzzzv.network.db.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "favourite_course"
)
data class FavouriteCourse(
    @PrimaryKey(autoGenerate = true) val id: String,
    val userId: String,
    val courseId: String
)