package com.example.studysmart.ui.view.component.task

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.studysmart.data.model.Task
import com.example.studysmart.ui.theme.Typography
import com.example.studysmart.utils.Priority

@Composable
fun TaskCard(
    modifier: Modifier = Modifier,
    task: Task,
    onClick: () -> Unit,
    onCheckBoxClick: () -> Unit
) {
    ElevatedCard(modifier = modifier.clickable { onClick() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TaskCheckBox(
                isComplete = task.isComplete,
                borderColor = Priority.fromInt(task.priority).color,
                onCheckBoxClick = {},
            )

            Spacer(modifier = Modifier.width(8.dp))

            Column {
                Text(
                    text = task.title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = Typography.titleMedium,
                    textDecoration = when {
                        task.isComplete -> {
                            TextDecoration.LineThrough
                        }

                        else -> TextDecoration.None
                    }
                )
                Spacer(modifier = Modifier.height(4.dp))

                Text(text = "${task.dueDate}", style = Typography.bodySmall)
            }
        }
    }
}