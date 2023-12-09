package com.example.studysmart.ui.view.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studysmart.utils.Constance

@Composable
fun AddSubjectDialog(
    onDismissRequest: () -> Unit,
    onConfirmButtonClick: () -> Unit,
    title: String = "Add/Update Subject",
    isOpen: Boolean,
    selectedColor: List<Color>,
    onColorChange: (List<Color>) -> Unit,
    subjectName: String,
    goalHours: String,
    onSubjectNameChange: (String) -> Unit,
    onSubjectGoalHoursChange: (String) -> Unit,
) {
    //error handel message
    var subjectNameError by rememberSaveable { mutableStateOf<String?>(null) }
    var goalHoursError by rememberSaveable { mutableStateOf<String?>(null) }
    subjectNameError = when {
        subjectName.isBlank() -> "Please enter subject name."
        subjectName.length < 2 -> "Subject name is too short."
        subjectName.length > 20 -> "Subject name is too long."
        else -> null
    }
    goalHoursError = when {
        goalHours.isBlank() -> "Please enter goal study hours."
        goalHours.toFloatOrNull() == null -> "Invalid number."
        goalHours.toFloat() < 1f -> "Please set at least 1 hour."
        goalHours.toFloat() > 1000f -> "Please set a maximum of 1000 hours."
        else -> null
    }
    var subjectNameValue by remember { mutableStateOf("") }
    var goalHoursValue by remember { mutableStateOf("") }
    when {
        isOpen -> {
            AlertDialog(
                onDismissRequest = { onDismissRequest() },
                title = {
                    Text(text = title)
                },
                text = {
                    Column {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 16.dp),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Constance.subjectCardColor.forEach { colors ->
                                Box(
                                    modifier = Modifier
                                        .size(if (colors == selectedColor) 30.dp else 25.dp)
                                        .clip(CircleShape)
                                        .background(brush = Brush.verticalGradient(colors))
                                        .clickable { onColorChange(colors) }
                                        .border(
                                            width = 1.dp,
                                            color = if (colors == selectedColor) Color.Black else Color.Transparent,
                                            shape = CircleShape
                                        )
                                )
                            }
                        }
                        OutlinedTextField(
                            value = subjectNameValue,
                            onValueChange = { value ->
                                subjectNameValue = value
                                onSubjectNameChange(value)
                            },
                            label = { Text(text = "Subject Name") },
                            singleLine = true,
                            isError = subjectNameError != null && subjectName.isNotBlank(),
                            supportingText = { Text(text = subjectNameError.orEmpty()) }
                        )
                        Spacer(modifier = Modifier.height(10.dp))
                        OutlinedTextField(
                            value = goalHoursValue,
                            onValueChange = { value ->
                                goalHoursValue = value
                                onSubjectGoalHoursChange(value)
                            },
                            label = { Text(text = "Goal Study Hours") },
                            singleLine = true,
                            isError = goalHoursError != null && goalHours.isNotBlank(),
                            supportingText = { Text(text = goalHoursError.orEmpty()) },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            onConfirmButtonClick()
                            subjectNameValue = ""
                            goalHoursValue = ""
                        },
                        enabled = subjectNameError == null && goalHoursError == null
                    ) {
                        Text(text = "Save")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { onDismissRequest() }) {
                        Text(text = "Cancel")
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddSubjectDialogPreview() {
    var isAddSubjectDialogOpen by rememberSaveable {
        mutableStateOf(true)
    }
    var subjectName by remember { mutableStateOf("") }
    var goalHours by remember { mutableStateOf("") }
    var selectColor by remember { mutableStateOf(Constance.subjectCardColor.random()) }



    AddSubjectDialog(
        onDismissRequest = { isAddSubjectDialogOpen = false },
        onConfirmButtonClick = { isAddSubjectDialogOpen = false },
        isOpen = isAddSubjectDialogOpen,
        selectedColor = selectColor,
        onColorChange = { selectColor = it },
        subjectName = subjectName,
        goalHours = goalHours,
        onSubjectNameChange = { subjectName = it },
        onSubjectGoalHoursChange = { goalHours = it }
    )
}