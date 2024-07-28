package com.example.fstore.presentation.details.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fstore.ui.theme.BackGroundDarkShade
import com.example.fstore.ui.theme.StarColor

@Composable
fun CustomReviewStars(
    modifier: Modifier = Modifier, filledStar: Int = 0,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        for (i in 1..5) {
            Icon(
                imageVector = Icons.Filled.StarRate,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = if (i <= filledStar) StarColor
                else BackGroundDarkShade
            )
        }
    }
}