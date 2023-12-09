package com.example.studysmart.ui.view.navigation.route

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.studysmart.ui.view.component.screen.SessionScreen
import com.example.studysmart.ui.viewmodel.DashboardViewModel
import com.example.studysmart.ui.viewmodel.SessionViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator


@Destination
@Composable
fun SessionScreenRoute(navigator: DestinationsNavigator) {
    val viewModel: SessionViewModel = hiltViewModel()
    SessionScreen(
        onBackButtonClick = {
            navigator.navigateUp()
        }
    )
}
