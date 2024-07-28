package com.example.fstore.presentation.shop.post_shop_screen.filter.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomFilterTopAppBar(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    CenterAlignedTopAppBar(modifier = modifier.shadow(4.dp), title = {
        Text(
            text = "Filters", fontSize = 26.sp, fontWeight = FontWeight.Bold
        )
    }, navigationIcon = {
        IconButton(onClick = { onBackClick.invoke() }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                contentDescription = null,
                modifier = Modifier.size(26.dp)
            )
        }
    })
}