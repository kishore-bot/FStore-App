package com.example.fstore.presentation.utils

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.delay

@Composable
fun CustomMultiColoredProgressBar(modifier: Modifier = Modifier) {
    val colors = listOf(Color.Red, Color.Green, Color.Blue, Color.Yellow)
    var currentIndex by remember { mutableIntStateOf(0) }

    // Change color every second
    LaunchedEffect(Unit) {
        while (true) {
            delay(200) // Change color every second
            currentIndex = (currentIndex + 1) % colors.size
        }
    }
    CircularProgressIndicator(modifier = modifier,
        color = colors[currentIndex])
}