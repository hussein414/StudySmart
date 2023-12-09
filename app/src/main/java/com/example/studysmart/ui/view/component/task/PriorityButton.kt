package com.example.studysmart.ui.view.component.task

import android.text.style.LineBackgroundSpan
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun PriorityButton(
    modifier: Modifier = Modifier,
    label: String,
    backgroundColor: Color,
    boarderColor: Color,
    labelColor: Color,
    onclick: () -> Unit
) {
    Box(modifier = modifier
        .background(backgroundColor)
        .clickable { onclick() }
        .padding(5.dp)
        .border(1.dp, boarderColor, RoundedCornerShape(5.dp))
        .padding(5.dp), contentAlignment = Alignment.Center) {
        Text(text = label, color = labelColor)
    }
}