package com.example.fstore.presentation.utils.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fstore.ui.theme.BackGroundDarkShade
import com.example.fstore.ui.theme.StarColor


@Composable
fun CustomRatingStars(
    modifier: Modifier = Modifier, filledStar: Int = 0, totalRating: Int = 0, maxStat: Int = 5
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start,
        modifier = modifier
            .padding(end = 5.dp, bottom = 5.dp)
            .fillMaxWidth()

    ) {
        for (i in 1..maxStat) {
            Icon(
                imageVector = Icons.Filled.StarRate,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
                tint = if (i <= filledStar) StarColor
                else BackGroundDarkShade
            )
        }
        Spacer(modifier = Modifier.width(2.dp))
        Text(text = "($totalRating)")
    }
}