package com.example.fstore.presentation.details.components

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomDetailsSupportCard(modifier: Modifier = Modifier, text: String,onclick:()->Unit,color: Color = Color.Black) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement =Arrangement.SpaceBetween,
        modifier = modifier
        .fillMaxWidth()
        .height(45.dp)
        .clickable { onclick.invoke() }
        .border(
            width = 0.5.dp, color = Color.Black.copy(alpha = 0.2f),
        )
        .padding(10.dp)) {
        Text(text = text, fontSize = 19.sp, color = color)
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
            contentDescription = text,
            modifier = Modifier.size(
                15.dp
            )
        )
    }
}