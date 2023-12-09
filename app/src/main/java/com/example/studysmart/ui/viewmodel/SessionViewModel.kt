package com.example.studysmart.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.studysmart.data.repository.session.SessionRepository
import com.example.studysmart.data.repository.subject.SubjectRepository
import com.example.studysmart.data.repository.task.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class SessionViewModel @Inject constructor(
    private val sessionRepository: SessionRepository
) : ViewModel() {
}