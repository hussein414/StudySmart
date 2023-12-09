package com.example.studysmart.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.example.studysmart.data.model.Subject
import kotlinx.coroutines.flow.Flow

@Dao
interface SubjectDao {

    @Upsert
    suspend fun upsertSubject(subject: Subject)

    @Query("SELECT COUNT(*) FROM subject")
    fun getTotalSubjectCount(): Flow<Int>


    @Query("SELECT SUM(goalHours) FROM subject")
    fun getTotalGoalHours(): Flow<Float>

    @Query("SELECT * FROM subject WHERE subjectId =:subjectId")
    fun getSubjectById(subjectId: Int): Subject?


    @Query("DELETE FROM subject WHERE subjectId =:subjectId")
    suspend fun deleteSubject(subjectId: Int)


    @Query("SELECT * FROM subject")
    fun getAllSubject(): Flow<List<Subject>>
}