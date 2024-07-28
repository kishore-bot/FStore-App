package com.example.fstore.presentation.address.components

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CustomAddressFAB(modifier: Modifier = Modifier, onAddAddressClick: () -> Unit) {
    FloatingActionButton(
        onClick = { onAddAddressClick.invoke() },
        modifier = modifier,
        shape = CircleShape,
        containerColor = Color.Black
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Address",
            tint = Color.White,
            modifier = Modifier.size(30.dp)
        )

    }
}
