package com.example.studysmart.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.studysmart.data.db.dao.SessionDao
import com.example.studysmart.data.db.dao.SubjectDao
import com.example.studysmart.data.db.dao.TaskDao
import com.example.studysmart.data.db.type.ColorListConverter
import com.example.studysmart.data.model.Session
import com.example.studysmart.data.model.Subject
import com.example.studysmart.data.model.Task

@Database(
    entities = [Subject::class, Session::class, Task::class],
    version = 1
)
@TypeConverters(ColorListConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun subjectDao(): SubjectDao
    abstract fun taskDao(): TaskDao
    abstract fun sessionDao(): SessionDao
}