package com.example.studysmart.ui.view.component.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studysmart.ui.view.component.dialog.AddSubjectDialog
import com.example.studysmart.ui.view.component.dialog.DeleteDialog
import com.example.studysmart.ui.view.component.session.studySessionList
import com.example.studysmart.ui.view.component.subject.SubjectOverviewSection
import com.example.studysmart.ui.view.component.subject.SubjectScreenTopBar
import com.example.studysmart.ui.view.component.task.taskList
import com.example.studysmart.utils.Constance
import com.example.studysmart.utils.sessions
import com.example.studysmart.utils.task


@Composable
fun SubjectScreen(
    onBackButtonClick: () -> Unit,
    onAddTaskButtonClick: () -> Unit,
    onTaskCardClick: (Int?) -> Unit,
) {
    val listState = rememberLazyListState()
    val isFABExpanded by remember { derivedStateOf { listState.firstVisibleItemIndex == 0 } }

    var isEditSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }

    val subjectName by remember {
        mutableStateOf("")
    }
    val goalHours by remember {
        mutableStateOf("")
    }

    val selectColor by remember {
        mutableStateOf(Constance.subjectCardColor.random())
    }

    AddSubjectDialog(
        isOpen = isEditSubjectDialogOpen,
        subjectName = subjectName,
        goalHours = goalHours,
        onSubjectNameChange = { },
        onSubjectGoalHoursChange = { },
        selectedColor = selectColor,
        onColorChange = { },
        onDismissRequest = { isEditSubjectDialogOpen = false },
        onConfirmButtonClick = { isEditSubjectDialogOpen = false }
    )

    DeleteDialog(
        isOpen = isDeleteSubjectDialogOpen,
        title = "Delete Subject?",
        bodyText = "Are you sure, you want to delete this subject? All related " +
                "tasks and study sessions will be permanently removed. This action can not be undone",
        onDismissRequest = { isDeleteSubjectDialogOpen = false },
        onConfirmButtonClick = {
            isDeleteSubjectDialogOpen = false
        }
    )

    DeleteDialog(
        isOpen = isDeleteSessionDialogOpen,
        title = "Delete Session?",
        bodyText = "Are you sure, you want to delete this session? Your studied hours will be reduced " +
                "by this session time. This action can not be undone.",
        onDismissRequest = { isDeleteSessionDialogOpen = false },
        onConfirmButtonClick = {
            isDeleteSessionDialogOpen = false
        }
    )

    Scaffold(
        topBar = {
            SubjectScreenTopBar(
                title = "English",
                onBackButtonClick = onBackButtonClick,
                onDeleteButtonClick = { isDeleteSubjectDialogOpen = true },
                onEditButtonClick = { isEditSubjectDialogOpen = true })
        }, floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddTaskButtonClick,
                icon = { Icon(imageVector = Icons.Default.Add, contentDescription = "Add") },
                text = { Text(text = "Add Task") },
                expanded = isFABExpanded
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                SubjectOverviewSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    studiedHours = "5",
                    goalHours = "5",
                    progress = 0.5f
                )
            }
            taskList(
                sectionTitle = "UPCOMING TASKS",
                emptyListSubject = "You don't have any upcoming tasks.\n " +
                        "Click the + button to add new task.",
                tasks = task,
                onCheckBoxClick = { },
                onTaskCardClick = onTaskCardClick
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
            taskList(
                sectionTitle = "COMPLETED TASKS",
                emptyListSubject = "You don't have any completed tasks.\n " +
                        "Click the check box on completion of task.",
                tasks = task,
                onCheckBoxClick = { },
                onTaskCardClick = onTaskCardClick
            )
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }

            studySessionList(
                sectionTitle = "RECENT STUDY SESSIONS",
                emptyListSubject = "You don't have any recent study sessions.\n " +
                        "Start a study session to begin recording your progress.",
                session = sessions,
                onDeleteIconClick = { isDeleteSessionDialogOpen = true }
            )
        }
    }
}