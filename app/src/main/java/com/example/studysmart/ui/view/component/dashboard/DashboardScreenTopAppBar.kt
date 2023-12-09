package com.example.studysmart.ui.view.component.dashboard

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.studysmart.ui.theme.Typography


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DashboardScreenTopAppBar() {
    CenterAlignedTopAppBar(title = {
        Text(text = "Study Smart", style = Typography.headlineMedium)
    })
}