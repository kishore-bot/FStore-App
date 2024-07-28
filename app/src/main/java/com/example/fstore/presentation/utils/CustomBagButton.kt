package com.example.fstore.presentation.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CustomBagButton(modifier: Modifier = Modifier,onBagClick: () -> Unit) {
    Card(
        modifier = modifier.padding(start = 15.dp, top = 15.dp),
        onClick = { onBagClick() },
        shape = CircleShape,
        colors = CardDefaults.cardColors(Color.Red),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.ShoppingBag,
            contentDescription = "Bag Button",
            tint = Color.White,
            modifier = Modifier
                .padding(8.dp)
                .size(25.dp)

        )
    }
}