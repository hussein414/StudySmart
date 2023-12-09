package com.example.studysmart.data.repository.session

import com.example.studysmart.data.db.dao.SessionDao
import com.example.studysmart.data.model.Session
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SessionRepositoryImpl @Inject constructor(private val sessionDao: SessionDao) :
    SessionRepository {
    override suspend fun insertSession(session: Session) = sessionDao.insertSession(session = session)

    override suspend fun deleteSession(session: Session) = sessionDao.deleteSession(session = session)

    override fun getAllSessions(): Flow<List<Session>> = sessionDao.getAllSessions()

    override fun getRecentSessionsForSubject(subjectId: Int): Flow<List<Session>> =
        sessionDao.getRecentSessionsForSubject(subjectId = subjectId)

    override fun getTotalSessionsDuration(): Flow<Long> =
        sessionDao.getTotalSessionsDuration()

    override fun getTotalSessionsDurationBySubjectId(subjectId: Int): Flow<Long> =
        sessionDao.getTotalSessionsDurationBySubjectId(subjectId = subjectId)

    override fun deleteSessionsBySubjectId(subjectId: Int) =
        sessionDao.deleteSessionsBySubjectId(subjectId = subjectId)
}