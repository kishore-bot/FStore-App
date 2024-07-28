package com.example.fstore.presentation.reviews.components


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fstore.ui.theme.StarColor

@Composable
fun CustomReviewBar(
    modifier: Modifier = Modifier, noOfStars: Int, totalRating: Int, ratingNo: Int
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        for (i in 1..5) {
            if (i <= 5 - noOfStars) Icon(
                imageVector = Icons.Default.Star,
                contentDescription = "Rating",
                tint = Color.Transparent,
                modifier = Modifier.size(20.dp)
            )
            else {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Rating",
                    tint = StarColor,
                    modifier = Modifier.size(20.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(5.dp))
        LinearProgressSection(ratingNo = ratingNo, totalRating = totalRating)
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = ratingNo.toString())
    }
}
