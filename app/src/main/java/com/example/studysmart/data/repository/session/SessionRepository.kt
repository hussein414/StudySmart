package com.example.studysmart.data.repository.session


import com.example.studysmart.data.model.Session
import kotlinx.coroutines.flow.Flow

interface SessionRepository {
    suspend fun insertSession(session: Session)
    suspend fun deleteSession(session: Session)
    fun getAllSessions(): Flow<List<Session>>
    fun getRecentSessionsForSubject(subjectId: Int): Flow<List<Session>>
    fun getTotalSessionsDuration(): Flow<Long>
    fun getTotalSessionsDurationBySubjectId(subjectId: Int): Flow<Long>
    fun deleteSessionsBySubjectId(subjectId: Int)

}