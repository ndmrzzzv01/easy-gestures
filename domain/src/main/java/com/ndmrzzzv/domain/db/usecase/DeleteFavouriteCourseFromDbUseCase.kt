package com.ndmrzzzv.domain.db.usecase

import com.ndmrzzzv.domain.db.model.FavouriteCourse
import com.ndmrzzzv.domain.db.repository.CourseDbRepository

class DeleteFavouriteCourseFromDbUseCase(
    private val courseDbRepository: CourseDbRepository
) {

    suspend operator fun invoke(id: String) {
        return courseDbRepository.deleteFavouriteCourseFromDb(id)
    }

}