package com.example.studysmart.di

import com.example.studysmart.data.repository.session.SessionRepository
import com.example.studysmart.data.repository.session.SessionRepositoryImpl
import com.example.studysmart.data.repository.subject.SubjectRepository
import com.example.studysmart.data.repository.subject.SubjectRepositoryImpl
import com.example.studysmart.data.repository.task.TaskRepository
import com.example.studysmart.data.repository.task.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindSubjectRepository(subjectRepositoryImpl: SubjectRepositoryImpl): SubjectRepository


    @Singleton
    @Binds
    abstract fun bindTaskRepository(taskRepositoryImpl: TaskRepositoryImpl): TaskRepository


    @Singleton
    @Binds
    abstract fun bindSessionRepository(sessionRepositoryImpl: SessionRepositoryImpl): SessionRepository
}