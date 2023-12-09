package com.example.studysmart.ui.view.component.session

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.studysmart.ui.theme.Blue

@Composable
fun ButtonsSection(
    modifier: Modifier,
    startButtonClick: () -> Unit,
    cancelButtonClick: () -> Unit,
    finishButtonClick: () -> Unit,
) {
    Row(
        modifier = modifier, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(
            onClick = cancelButtonClick,
            colors = ButtonDefaults.buttonColors(containerColor = Blue)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp), text = "Cancel"
            )
        }
        Button(
            onClick = startButtonClick,
            colors = ButtonDefaults.buttonColors(containerColor = Blue)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp), text = "Start"
            )
        }
        Button(
            onClick = finishButtonClick,
            colors = ButtonDefaults.buttonColors(containerColor = Blue)
        ) {
            Text(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp), text = "Finish"
            )
        }
    }
}

@Preview
@Composable
fun test() {
    ButtonsSection(modifier = Modifier
        .fillMaxWidth()
        .padding(8.dp),
        startButtonClick = { /*TODO*/ },
        cancelButtonClick = { /*TODO*/ },
        finishButtonClick = {/*TODO*/ })
}