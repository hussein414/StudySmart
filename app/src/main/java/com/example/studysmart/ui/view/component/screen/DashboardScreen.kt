package com.example.studysmart.ui.view.component.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.studysmart.data.event.DashboardEvent
import com.example.studysmart.ui.theme.Blue
import com.example.studysmart.ui.view.component.dialog.AddSubjectDialog
import com.example.studysmart.ui.view.component.count.CountCardSection
import com.example.studysmart.ui.view.component.dashboard.DashboardScreenTopAppBar
import com.example.studysmart.ui.view.component.dialog.DeleteDialog
import com.example.studysmart.ui.view.component.session.studySessionList
import com.example.studysmart.ui.view.component.subject.SubjectCardSection
import com.example.studysmart.ui.view.component.task.taskList
import com.example.studysmart.data.state.DashboardState
import com.example.studysmart.utils.sessions
import com.example.studysmart.utils.task


@Composable
fun DashboardScreen(
    onSubjectCardClick: (Int?) -> Unit,
    onTaskCardClick: (Int?) -> Unit,
    onStartSessionButtonClick: () -> Unit,
    state: DashboardState,
    onEvent: (DashboardEvent) -> Unit
) {

    var isAddSubjectDialogOpen by rememberSaveable { mutableStateOf(false) }
    var isDeleteSessionDialogOpen by rememberSaveable { mutableStateOf(false) }


    AddSubjectDialog(
        isOpen = isAddSubjectDialogOpen,
        subjectName = state.subjectName,
        goalHours = state.goalStudyHours,
        selectedColor = state.subjectCardColors,
        onSubjectNameChange = { onEvent(DashboardEvent.OnSubjectNameChange(it)) },
        onSubjectGoalHoursChange = { onEvent(DashboardEvent.OnGoalStudyHoursChange(it)) },
        onColorChange = { onEvent(DashboardEvent.OnSubjectCardColorChange(it)) },
        onDismissRequest = { isAddSubjectDialogOpen = false },
        onConfirmButtonClick = {
            onEvent(DashboardEvent.SaveSubject)
            isAddSubjectDialogOpen = false
        }
    )


    DeleteDialog(
        isOpen = isDeleteSessionDialogOpen,
        title = "Delete Session?",
        bodyText = "Are you sure, you want to delete this session? Your studied hours will be reduced " +
                "by this session time. This action can not be undone.",
        onDismissRequest = { isDeleteSessionDialogOpen = false },
        onConfirmButtonClick = {
            onEvent(DashboardEvent.DeleteSession)
            isDeleteSessionDialogOpen = false
        }
    )
    Scaffold(topBar = { DashboardScreenTopAppBar() }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                CountCardSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    subjectCount = state.totalSubjectCount,
                    studiedHours = state.totalStudiedHours.toString(),
                    goalHours = state.totalGoalStudyHours.toString()
                )
            }
            item {
                SubjectCardSection(
                    modifier = Modifier.fillMaxWidth(),
                    subjectList = state.subjects,
                    onAddIconClicked = { isAddSubjectDialogOpen = true },
                    onSubjectCardClick = onSubjectCardClick
                )
            }
            item {
                Button(
                    onClick = { onStartSessionButtonClick() },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 48.dp, vertical = 20.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Blue)
                ) {
                    Text(text = "start study sessions")
                }
            }
            taskList(sectionTitle = "UPCOMING TASKS",
                tasks = task,
                emptyListSubject = "You don't have any upcoming tasks. \n " + "Click the + button in subject screen to add new task.",
                onTaskCardClick = onTaskCardClick,
                onCheckBoxClick = {
                    onEvent(DashboardEvent.OnTaskIsCompleteChange(it))
                })
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
            studySessionList(sectionTitle = "RECENT STUDY SESSION",
                emptyListSubject = "You don't have any upcoming tasks. \n " + "Click the + button in subject screen to add new task.",
                session = sessions,
                onDeleteIconClick = {
                    isDeleteSessionDialogOpen = true
                    onEvent(DashboardEvent.OnDeleteSessionButtonClick(it))
                })
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}