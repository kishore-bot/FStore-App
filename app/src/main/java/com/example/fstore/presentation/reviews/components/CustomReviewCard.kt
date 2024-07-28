package com.example.fstore.presentation.reviews.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.domain.model.rec_sub.ReviewModel
import com.example.fstore.presentation.details.components.CustomReviewStars
import com.example.fstore.utils.cap

@Composable
fun CustomReviewCard(modifier: Modifier = Modifier, review: ReviewModel) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .shadow(
                elevation = 1.dp,
                shape = RoundedCornerShape(10.dp),
            )
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(15.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = review.user.cap(), fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                CustomReviewStars(filledStar = review.rating)
                Text(text = review.createdAt.substring(0,9), color = Color.Gray)
            }
            Spacer(modifier = Modifier.height(10.dp))
            review.comment?.let {
                Text(
                    text = it, overflow = TextOverflow.Clip
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}
