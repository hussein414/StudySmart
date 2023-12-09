package com.example.studysmart.data.repository.task

import com.example.studysmart.data.db.dao.TaskDao
import com.example.studysmart.data.model.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepositoryImpl @Inject constructor(private val taskDao: TaskDao) : TaskRepository {

    override suspend fun upsertTask(task: Task) = taskDao.upsertTask(task = task)

    override suspend fun deleteTask(taskId: Int) = taskDao.deleteTask(taskId = taskId)

    override suspend fun deleteTasksBySubjectId(subjectId: Int) =
        taskDao.deleteTasksBySubjectId(subjectId = subjectId)

    override suspend fun getTaskById(taskId: Int): Task? =
        taskDao.getTaskById(taskId = taskId)

    override fun getTasksForSubject(subjectId: Int): Flow<List<Task>> =
        taskDao.getTasksForSubject(subjectId = subjectId)

    override fun getAllTasks(): Flow<List<Task>> = taskDao.getAllTasks()
}