package com.example.fstore.presentation.reviews.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.domain.model.RatingModel

@Composable
fun CustomReviewStatSection(modifier: Modifier = Modifier, rating: RatingModel) {
    Row(
        modifier = modifier.padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Column {
            Text(text = "${rating.productRating}", fontSize = 40.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "${rating.totalRating} rating",
                fontSize = 17.sp,
                fontWeight = FontWeight.Normal,
                color = Color.Gray
            )
        }
        Spacer(modifier = Modifier.width(30.dp))
        Column {
            for (i in 5 downTo 1) {
                val ratingNo = when (i) {
                    5 -> rating.starCounts.star5
                    4 -> rating.starCounts.star4
                    3 -> rating.starCounts.star3
                    2 -> rating.starCounts.star2
                    1 -> rating.starCounts.star1
                    else -> 0
                }
                CustomReviewBar(
                    noOfStars = i, totalRating = rating.totalRating, ratingNo = ratingNo
                )
            }

        }
    }
}