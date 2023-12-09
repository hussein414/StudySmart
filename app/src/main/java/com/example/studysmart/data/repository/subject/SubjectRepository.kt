package com.example.studysmart.data.repository.subject

import com.example.studysmart.data.model.Subject
import kotlinx.coroutines.flow.Flow

interface SubjectRepository {
    suspend fun upsertSubject(subject: Subject)

    fun getTotalSubjectCount(): Flow<Int>

    fun getTotalGoalHours(): Flow<Float>

    fun getSubjectById(subjectId: Int): Subject?

    suspend fun deleteSubject(subjectId: Int)

    fun getAllSubject(): Flow<List<Subject>>

}