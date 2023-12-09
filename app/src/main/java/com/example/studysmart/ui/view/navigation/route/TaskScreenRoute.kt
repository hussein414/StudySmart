package com.example.studysmart.ui.view.navigation.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studysmart.ui.view.navigation.argument.TaskScreenNavArgs
import com.example.studysmart.ui.view.component.screen.TaskScreen
import com.example.studysmart.ui.viewmodel.TaskViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(navArgsDelegate = TaskScreenNavArgs::class)
@Composable
fun TaskScreenRoute(navigator: DestinationsNavigator) {
    val viewModel: TaskViewModel = hiltViewModel()
    TaskScreen(onBackButtonClick = { navigator.navigateUp() })
}