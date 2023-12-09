package com.example.studysmart.ui.view.component.task

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.studysmart.R
import com.example.studysmart.data.model.Task
import com.example.studysmart.ui.theme.Typography


fun LazyListScope.taskList(
    sectionTitle: String,
    tasks: List<Task>,
    emptyListSubject: String,
    onTaskCardClick: (Int?) -> Unit,
    onCheckBoxClick: (Task) -> Unit
) {
    item {
        Text(
            text = sectionTitle, style = Typography.bodySmall, modifier = Modifier.padding(12.dp)
        )
    }
    if (tasks.isEmpty()) {
        item {
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(120.dp)
                        .align(Alignment.CenterHorizontally),
                    painter = painterResource(id = R.drawable.tasks),
                    contentDescription = emptyListSubject
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = emptyListSubject,
                    style = Typography.bodyLarge,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

            }
        }
    }
    items(tasks) { task ->
        TaskCard(
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
            task = task,
            onClick = { onTaskCardClick(task.taskId) },
            onCheckBoxClick = { onCheckBoxClick(task) })
    }
}