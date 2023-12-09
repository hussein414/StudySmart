package com.example.studysmart.ui.view.component.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowDropDown
import androidx.compose.material.icons.rounded.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studysmart.ui.theme.Red
import com.example.studysmart.ui.theme.Typography
import com.example.studysmart.ui.view.component.dialog.DeleteDialog
import com.example.studysmart.ui.view.component.dialog.TaskDatePicker
import com.example.studysmart.ui.view.component.subject.SubjectListBottomSheet
import com.example.studysmart.ui.view.component.task.PriorityButton
import com.example.studysmart.ui.view.component.task.TaskScreenTopBar
import com.example.studysmart.utils.Priority
import com.example.studysmart.utils.changeMillisToDateString
import com.example.studysmart.utils.subject
import kotlinx.coroutines.launch
import java.time.Instant


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskScreen(
    onBackButtonClick: () -> Unit,
) {
    var title by remember {
        mutableStateOf("")
    }
    var description by remember {
        mutableStateOf("")
    }
    var taskTitleError by rememberSaveable { mutableStateOf<String?>(null) }

    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }


    var isDatePickerDialogOpen by rememberSaveable { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )


    var isBottomSheetOpen by remember { mutableStateOf(false) }
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    DeleteDialog(
        isOpen = isDeleteDialogOpen,
        title = "Delete Task?",
        bodyText = "Are you sure, you want to delete this task? " +
                "This action can not be undone.",
        onDismissRequest = { isDeleteDialogOpen = false },
        onConfirmButtonClick = {
            isDeleteDialogOpen = false
        }
    )


    TaskDatePicker(
        state = datePickerState,
        isOpen = isDatePickerDialogOpen,
        onDismissRequest = { isDatePickerDialogOpen = false },
        onConfirmButtonClicked = { isDatePickerDialogOpen = false })



    SubjectListBottomSheet(
        sheetState = sheetState,
        isOpen = isBottomSheetOpen,
        subjects = subject,
        onSubjectClicked = {
            scope.launch {
                sheetState.hide()
            }.invokeOnCompletion {
                when {
                    !sheetState.isVisible -> isBottomSheetOpen = false
                }
            }
        },
        onDismissRequest = { isBottomSheetOpen = false })


    taskTitleError = when {
        title.isBlank() -> "Please enter task title."
        title.length < 4 -> "Task title is too short."
        title.length > 30 -> "Task title is too long."
        else -> null
    }


    Scaffold(topBar = {
        TaskScreenTopBar(
            isTaskExist = true,
            isComplete = false,
            checkBoxBorderColor = Red,
            onBackButtonClick = onBackButtonClick,
            onDeleteButtonClick = { isDeleteDialogOpen = true }) {

        }
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 12.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = { title = it },
                label = { Text(text = "Title") },
                singleLine = true,
                isError = taskTitleError != null && title.isNotBlank(),
                supportingText = { Text(text = taskTitleError.orEmpty()) }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = description,
                onValueChange = { description = it },
                label = { Text(text = "Description") },
            )

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Due Date", style = Typography.bodySmall)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = datePickerState.selectedDateMillis.changeMillisToDateString(),
                    style = Typography.bodyLarge
                )

                IconButton(onClick = { isDatePickerDialogOpen = true }) {
                    Icon(imageVector = Icons.Rounded.DateRange, contentDescription = "Date")
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(text = "Priority", style = Typography.bodySmall)

            Spacer(modifier = Modifier.height(10.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Priority.entries.forEach { priority ->
                    PriorityButton(
                        modifier = Modifier.weight(1f),
                        label = priority.title,
                        backgroundColor = priority.color,
                        boarderColor = if (priority == Priority.MEDIUM) White else Transparent,
                        labelColor = if (priority == Priority.MEDIUM) White else White.copy(alpha = 0.7f),
                        onclick = {})
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(text = "Related to Subject", style = Typography.bodySmall)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "English", style = Typography.bodyLarge)

                IconButton(onClick = { isBottomSheetOpen = true }) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowDropDown,
                        contentDescription = "Select Subject"
                    )
                }
            }

        }

    }
}