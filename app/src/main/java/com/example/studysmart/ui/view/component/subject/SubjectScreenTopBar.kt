package com.example.studysmart.ui.view.component.subject

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import com.example.studysmart.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectScreenTopBar(
    title: String,
    onBackButtonClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
    onEditButtonClick: () -> Unit,
) {
    LargeTopAppBar(navigationIcon = {
        IconButton(onClick = { onBackButtonClick() }) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = "navigate back"
            )
        }
    }, title = {
        Text(
            text = title,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = Typography.headlineSmall
        )
    }, actions = {
        IconButton(onClick = { onDeleteButtonClick() }) {
            Icon(
                imageVector = Icons.Rounded.Delete,
                contentDescription = "Delete Subject"
            )
        }
        IconButton(onClick = { onEditButtonClick() }) {
            Icon(
                imageVector = Icons.Rounded.Edit,
                contentDescription = "Edit Subject"
            )
        }
    })
}