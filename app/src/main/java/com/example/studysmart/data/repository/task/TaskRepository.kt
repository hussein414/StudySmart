package com.example.studysmart.data.repository.task

import com.example.studysmart.data.model.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    suspend fun upsertTask(task: Task)
    suspend fun deleteTask(taskId: Int)
    suspend fun deleteTasksBySubjectId(subjectId: Int)
    suspend fun getTaskById(taskId: Int): Task?
    fun getTasksForSubject(subjectId: Int): Flow<List<Task>>
    fun getAllTasks(): Flow<List<Task>>

}