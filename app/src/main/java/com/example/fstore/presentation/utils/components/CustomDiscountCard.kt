package com.example.fstore.presentation.utils.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomDiscountCard(
    modifier: Modifier = Modifier, discountPrice: Int = 0, isNew: Boolean = false
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(if (discountPrice != 0) Color.Red else Color.Black)
            .padding(5.dp),
    ) {
        if (discountPrice != 0 && !isNew) {
            Text(
                text = "-${discountPrice}%",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 13.sp
            )
        } else if (isNew ) {
            Text(
                text = "New", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp
            )
        }
    }
}