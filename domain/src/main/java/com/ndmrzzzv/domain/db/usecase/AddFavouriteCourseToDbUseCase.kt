package com.ndmrzzzv.domain.db.usecase

import com.ndmrzzzv.domain.db.model.FavouriteCourse
import com.ndmrzzzv.domain.db.repository.CourseDbRepository

class AddFavouriteCourseToDbUseCase(
    private val courseDbRepository: CourseDbRepository
) {

    suspend operator fun invoke(course: FavouriteCourse) {
        return courseDbRepository.addFavouriteCourseToDb(course)
    }

}