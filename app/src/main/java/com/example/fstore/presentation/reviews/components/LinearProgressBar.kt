package com.example.fstore.presentation.reviews.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor


@Composable
fun LinearProgressSection(modifier: Modifier = Modifier, ratingNo: Int, totalRating: Int) {

    val progress = ratingNo.toFloat() / totalRating.toFloat()
    Box(
        modifier = modifier
            .width(130.dp)
            .clip(RoundedCornerShape(5.dp))
            .height(10.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth()
                .background(BackgroundColor)
        )
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(5.dp))
                .fillMaxWidth(progress)
                .height(20.dp)
                .background(ButtonColor)

        )
    }
}