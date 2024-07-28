package com.example.fstore.presentation.details.view_all.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomViewAllTopScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(text = "Similar Products", fontSize = 26.sp, fontWeight = FontWeight.W700)
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick.invoke() }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                    contentDescription = "Null",
                    modifier = Modifier.size(26.dp)
                )
            }

        },
    )
}