package com.example.studysmart.di

import android.app.Application
import androidx.room.Room
import com.example.studysmart.data.db.AppDatabase
import com.example.studysmart.data.db.dao.SessionDao
import com.example.studysmart.data.db.dao.SubjectDao
import com.example.studysmart.data.db.dao.TaskDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(
            application.applicationContext,
            AppDatabase::class.java,
            "study_db"
        )
            .build()


    @Singleton
    @Provides
    fun providesSubjectDao(appDatabase: AppDatabase): SubjectDao = appDatabase.subjectDao()


    @Singleton
    @Provides
    fun providesTaskDao(appDatabase: AppDatabase): TaskDao = appDatabase.taskDao()




    @Singleton
    @Provides
    fun providesSessionDao(appDatabase: AppDatabase): SessionDao = appDatabase.sessionDao()
}