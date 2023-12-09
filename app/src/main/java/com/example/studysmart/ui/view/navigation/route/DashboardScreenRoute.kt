package com.example.studysmart.ui.view.navigation.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.studysmart.ui.view.component.screen.DashboardScreen
import com.example.studysmart.ui.view.navigation.argument.SubjectScreenNavArgs
import com.example.studysmart.ui.view.navigation.argument.TaskScreenNavArgs
import com.example.studysmart.ui.view.navigation.route.destinations.SessionScreenRouteDestination
import com.example.studysmart.ui.view.navigation.route.destinations.SubjectScreenRouteDestination
import com.example.studysmart.ui.view.navigation.route.destinations.TaskScreenRouteDestination
import com.example.studysmart.ui.viewmodel.DashboardViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun DashboardScreenRoute(navigator: DestinationsNavigator) {
    val viewModel: DashboardViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    DashboardScreen(
        state = state,
        onSubjectCardClick = { subjectId ->
            subjectId?.let {
                val navArgs = SubjectScreenNavArgs(subjectId = subjectId)
                navigator.navigate(SubjectScreenRouteDestination(navArgs = navArgs))
            }
        },
        onTaskCardClick = { taskId ->
            taskId?.let {
                val navArgs = TaskScreenNavArgs(subjectId = null, taskId = taskId)
                navigator.navigate(TaskScreenRouteDestination(navArgs = navArgs))
            }
        },
        onStartSessionButtonClick = {
            navigator.navigate(SessionScreenRouteDestination())
        },
        onEvent = viewModel::onEvent
    )
}