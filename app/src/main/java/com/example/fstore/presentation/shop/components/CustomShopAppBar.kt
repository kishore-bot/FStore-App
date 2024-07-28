package com.example.fstore.presentation.shop.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomShopAppBar(modifier: Modifier = Modifier) {
    MediumTopAppBar(
        modifier = modifier,
        title = {
            Text(text = "Categories", fontSize = 33.sp, fontWeight = FontWeight.W700)
        },
    )
}