package com.example.fstore.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.domain.model.DetailsModel
import com.example.fstore.presentation.utils.components.CustomRatingStars
import com.example.fstore.ui.theme.TextLighterShade
import com.example.fstore.utils.cap
import kotlin.math.roundToInt


@Composable
fun CustomDetailsCard(modifier: Modifier = Modifier, details: DetailsModel) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = details.name.uppercase(), fontSize = 25.sp, fontWeight = FontWeight.Bold)
            Text(
                text = "$${details.price.roundToInt()}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = details.category.cap(),
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = TextLighterShade
        )
        Spacer(modifier = Modifier.height(10.dp))
        CustomRatingStars(filledStar = details.rating, totalRating = details.noOfRating)
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = "No orders: ${details.popularity}",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = details.description,
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = Color.Black
        )
    }
}