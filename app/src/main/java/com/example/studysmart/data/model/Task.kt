package com.example.studysmart.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task")
data class Task(
    val title: String,
    val description: String,
    val dueDate: Long,
    val priority: Int,
    val relatedSubject: String,
    val isComplete: Boolean,
    @PrimaryKey(autoGenerate = true)
    val taskId: Int? = null,
    val taskSubjectId: Int,
)