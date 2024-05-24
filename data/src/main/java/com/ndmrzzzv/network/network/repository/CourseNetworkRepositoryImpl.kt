package com.ndmrzzzv.network.network.repository

import com.google.firebase.database.DatabaseReference
import com.ndmrzzzv.domain.network.data.Course
import com.google.firebase.database.ktx.getValue
import com.ndmrzzzv.domain.network.data.Lesson
import com.ndmrzzzv.domain.network.repository.CourseNetworkRepository
import com.ndmrzzzv.network.utils.awaitSingle

class CourseNetworkRepositoryImpl(
    private val databaseReference: DatabaseReference
): CourseNetworkRepository {

    override suspend fun getAllCourses(): List<Course> {
        val listOfCourse = mutableListOf<Course>()
        val references = databaseReference.child("Course").awaitSingle()?.children
        if (references != null) {
            for (item in references) {
                val course = item.getValue<Course>()
                if (course != null) {
                    listOfCourse.add(course)
                }
            }
        }
        return listOfCourse
    }

    override suspend fun getAllLessonsByCourseId(courseId: Int): List<Lesson> {
        val lessons = mutableListOf<Lesson>()
        val reference = databaseReference.child("Lesson").awaitSingle()?.children
        if (reference != null) {
            for (item in reference) {
                val lesson = item.getValue<Lesson>()
                if (lesson != null && lesson.courseId == courseId) {
                    lessons.add(lesson)
                }
            }
        }
        return lessons
    }

}