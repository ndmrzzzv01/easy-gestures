package com.ndmrzzzv.domain.db.usecase

import com.ndmrzzzv.domain.db.model.FavouriteCourse
import com.ndmrzzzv.domain.db.repository.CourseDbRepository

class GetAllFavouriteCoursesUseCase(
    private val courseDbRepository: CourseDbRepository
) {

    suspend operator fun invoke(): List<FavouriteCourse> {
        return courseDbRepository.getAllFavouriteCourses()
    }

}