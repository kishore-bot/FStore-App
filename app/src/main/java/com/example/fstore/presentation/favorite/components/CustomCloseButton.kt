package com.example.fstore.presentation.favorite.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomCloseButton(modifier: Modifier = Modifier, onCloseClick: () -> Unit) {
    IconButton(onClick = { onCloseClick() }, modifier = modifier) {
        Icon(
            imageVector = Icons.Default.Cancel,
            contentDescription = "Close Button",
            modifier = Modifier.size(25.dp),
            tint = Color.Gray
        )
    }
}