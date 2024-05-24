package com.ndmrzzzv.network

import androidx.room.Room
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.ndmrzzzv.domain.db.repository.CourseDbRepository
import com.ndmrzzzv.domain.network.repository.CourseNetworkRepository
import com.ndmrzzzv.network.db.EasyGesturesDatabase
import com.ndmrzzzv.network.db.dao.CourseDao
import com.ndmrzzzv.network.db.repository.CourseDbRepositoryImpl
import com.ndmrzzzv.network.network.repository.CourseNetworkRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


private const val DATABASE_NAME = "easy_gestures_db"

val dataModule = module {

    // Database
    factory<CourseDbRepository> { CourseDbRepositoryImpl(get()) }

    single<CourseDao> {
        val db = Room.databaseBuilder(
            androidContext(),
            EasyGesturesDatabase::class.java,
            DATABASE_NAME
        ).build()

        return@single db.getCourseDao()
    }

    // Network
    factory<CourseNetworkRepository> { CourseNetworkRepositoryImpl(get()) }

    single<DatabaseReference> { Firebase.database.reference }

}