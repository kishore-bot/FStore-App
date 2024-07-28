package com.example.fstore.presentation.utils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fstore.ui.theme.ButtonColor

@Composable
fun CustomFavButton(modifier: Modifier = Modifier, isFav: Boolean = false) {

    Card(
        modifier = modifier.padding(5.dp),
        shape = CircleShape,
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.elevatedCardElevation(2.dp)
    ) {
        Icon(
            imageVector = if (isFav) Icons.Filled.Favorite else Icons.Default.FavoriteBorder,
            contentDescription = "Favorite Button",
            tint = if (isFav) ButtonColor else Color.Gray,
            modifier = Modifier
                .size(40.dp)
                .padding(8.dp)
        )
    }
}
