package com.example.fstore.presentation.profile.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun CustomProfileButton(
    modifier: Modifier = Modifier, mainText: String, subText: String, onclick: () -> Unit
) {
    Column(modifier = modifier
        .padding(top = 3.dp)
        .clickable { onclick.invoke() }) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = mainText, fontSize = 16.sp, fontWeight = FontWeight.SemiBold)
                Text(text = subText, fontSize = 13.sp, fontWeight = FontWeight.Light)
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = "Null"
            )
        }
        HorizontalDivider(thickness = 0.15.dp, color = Color.Gray)
    }

}