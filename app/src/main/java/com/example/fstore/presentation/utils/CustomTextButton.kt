package com.example.fstore.presentation.utils

import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    onCustomTextButtonClick: () -> Unit,
    text: String,
    textColor: Color = Color.Black,
    fSize: TextUnit = 15.sp
) {
    TextButton(onClick = { onCustomTextButtonClick() }, modifier = modifier) {
        Text(text = text, fontWeight = FontWeight.SemiBold, fontSize = fSize, color = textColor)
    }
}