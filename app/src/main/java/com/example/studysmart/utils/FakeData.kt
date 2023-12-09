package com.example.studysmart.utils

import androidx.compose.ui.graphics.toArgb
import com.example.studysmart.data.model.Session
import com.example.studysmart.data.model.Subject
import com.example.studysmart.data.model.Task

val subject = listOf(
    Subject(
        name = "Farsi",
        goalHours = 1f,
        colors = Constance.subjectCardColor[0].map { it.toArgb() },
        0
    ),

)

val task = listOf(
    Task(
        title = "Prepare notes",
        description = "",
        dueDate = 1L,
        priority = 1,
        relatedSubject = "",
        isComplete = true,
        taskId = 0,
        taskSubjectId = 0
    ),
    Task(
        title = "Prepare notes",
        description = "",
        dueDate = 1L,
        priority = 0,
        relatedSubject = "",
        isComplete = false,
        taskId = 2,
        taskSubjectId = 0
    ),
    Task(
        title = "Prepare notes",
        description = "",
        dueDate = 1L,
        priority = 1,
        relatedSubject = "",
        isComplete = false,
        taskId = 0,
        taskSubjectId = 0
    ), Task(
        title = "Prepare notes",
        description = "",
        dueDate = 1L,
        priority = 1,
        relatedSubject = "",
        isComplete = false,
        taskId = 0,
        taskSubjectId = 0
    )
)

val sessions = listOf(
    Session(
        relatedToSubject = "English",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
    Session(
        relatedToSubject = "Farsi",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
    Session(
        relatedToSubject = "English",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
    Session(
        relatedToSubject = "Farsi",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
    Session(
        relatedToSubject = "Farsi",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
    Session(
        relatedToSubject = "Farsi",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
    Session(
        relatedToSubject = "Farsi",
        date = 0L,
        duration = 2,
        sessionSubjectId = 0,
        sessionId = 0
    ),
)