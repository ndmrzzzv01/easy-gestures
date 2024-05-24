package com.ndmrzzzv.network.db.module

import androidx.room.Room
import com.ndmrzzzv.domain.db.repository.CourseDbRepository
import com.ndmrzzzv.network.db.EasyGesturesDatabase
import com.ndmrzzzv.network.db.dao.CourseDao
import com.ndmrzzzv.network.db.repository.CourseDbRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

private const val DATABASE_NAME = "easy_gestures_db"

val dbModule = module {

    factory<CourseDbRepository> { CourseDbRepositoryImpl(get()) }

    single<CourseDao> {
        val db = Room.databaseBuilder(
            androidContext(),
            EasyGesturesDatabase::class.java,
            DATABASE_NAME
        ).build()

        return@single db.getCourseDao()
    }

}