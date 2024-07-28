package com.example.fstore.presentation.bag.componets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.ui.theme.TextLighterShade

@Composable
fun CustomBagTotalPrice(modifier: Modifier = Modifier, price: Int) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Total Amount : ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = TextLighterShade,
            modifier = Modifier.padding(start = 5.dp)
        )
        Text(
            text = "$ $price",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}