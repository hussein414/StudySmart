package com.example.studysmart.ui.view.navigation.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studysmart.ui.view.navigation.argument.SubjectScreenNavArgs
import com.example.studysmart.ui.view.component.screen.SubjectScreen
import com.example.studysmart.ui.view.navigation.argument.TaskScreenNavArgs
import com.example.studysmart.ui.view.navigation.route.destinations.TaskScreenRouteDestination
import com.example.studysmart.ui.viewmodel.DashboardViewModel
import com.example.studysmart.ui.viewmodel.SubjectViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(navArgsDelegate = SubjectScreenNavArgs::class)
@Composable
fun SubjectScreenRoute(navigator: DestinationsNavigator) {
    val viewModel: SubjectViewModel = hiltViewModel()
    SubjectScreen(
        onBackButtonClick = {
            navigator.navigateUp()
        },
        onAddTaskButtonClick = {
            val navArgs = TaskScreenNavArgs(subjectId = -1, taskId = null)
            navigator.navigate(TaskScreenRouteDestination(navArgs = navArgs))
        },
        onTaskCardClick = { taskId ->
            taskId?.let {
                val navArgs = TaskScreenNavArgs(subjectId = null, taskId = taskId)
                navigator.navigate(TaskScreenRouteDestination(navArgs = navArgs))
            }
        }
    )
}
