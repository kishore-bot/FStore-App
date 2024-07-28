package com.example.fstore.presentation.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.ui.theme.ButtonColor

@Composable
fun CustomButton(
    modifier: Modifier = Modifier, onCustomButtonClick: () -> Unit, buttonText: String
) {
    Button(
        onClick = { onCustomButtonClick() },
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(ButtonColor)
    ) {
        Text(
            text = buttonText,
            color = Color.White,
            fontSize = 16.sp,
            modifier = Modifier.padding(7.dp)
        )
    }
}