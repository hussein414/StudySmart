package com.example.studysmart.data.repository.subject

import com.example.studysmart.data.db.dao.SubjectDao
import com.example.studysmart.data.model.Subject
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SubjectRepositoryImpl @Inject constructor(private val subjectDao: SubjectDao) :
    SubjectRepository {
    override suspend fun upsertSubject(subject: Subject) =
        subjectDao.upsertSubject(subject = subject)

    override fun getTotalSubjectCount(): Flow<Int> =
        subjectDao.getTotalSubjectCount()

    override fun getTotalGoalHours(): Flow<Float> =
        subjectDao.getTotalGoalHours()

    override fun getSubjectById(subjectId: Int): Subject? =
        subjectDao.getSubjectById(subjectId = subjectId)

    override suspend fun deleteSubject(subjectId: Int) =
        subjectDao.deleteSubject(subjectId = subjectId)

    override fun getAllSubject(): Flow<List<Subject>> =
        subjectDao.getAllSubject()
}