package com.example.studysmart.ui.view.component.screen

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studysmart.ui.view.component.dialog.DeleteDialog
import com.example.studysmart.ui.view.component.session.ButtonsSection
import com.example.studysmart.ui.view.component.session.RelatedToSubjectSection
import com.example.studysmart.ui.view.component.session.SessionScreenTopAppBar
import com.example.studysmart.ui.view.component.session.TimerSection
import com.example.studysmart.ui.view.component.session.studySessionList
import com.example.studysmart.ui.view.component.subject.SubjectListBottomSheet
import com.example.studysmart.utils.sessions
import com.example.studysmart.utils.subject
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SessionScreen(
    onBackButtonClick: () -> Unit
) {
    var isDeleteDialogOpen by rememberSaveable { mutableStateOf(false) }

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

    Scaffold(topBar = {
        SessionScreenTopAppBar(onBackButtonClick = {})
    }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            item {
                TimerSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                )
            }
            item {
                RelatedToSubjectSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp),
                    relatedToSubject = "English",
                    selectSubjectButtonClick = { isBottomSheetOpen = true },
                    seconds = "12"
                )
            }
            item {
                ButtonsSection(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    startButtonClick = { /*TODO*/ },
                    cancelButtonClick = { /*TODO*/ },
                    finishButtonClick = {})
            }
            studySessionList(
                sectionTitle = "STUDY SESSIONS HISTORY",
                emptyListSubject = "You don't have any recent study sessions.\n " +
                        "Start a study session to begin recording your progress.",
                session = sessions,
                onDeleteIconClick = { isDeleteDialogOpen = true }
            )
        }
    }
}