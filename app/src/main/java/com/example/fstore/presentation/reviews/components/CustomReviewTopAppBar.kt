package com.example.fstore.presentation.reviews.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomReviewTopAppBar(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    TopAppBar(modifier = modifier
        .shadow(elevation = 6.dp)
        .shadow(elevation = 6.dp), title = {
        Text(
            text = "Rating & Reviews", fontSize = 20.sp, fontWeight = FontWeight.Bold
        )
    }, navigationIcon = {
        IconButton(onClick = { onBackClick.invoke() }) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
    })
}