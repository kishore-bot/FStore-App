package com.example.fstore.presentation.details.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.fstore.presentation.utils.CustomButton

@Composable
fun CustomDetailsBottomBar(modifier: Modifier = Modifier, onButtonClick: () -> Unit) {
    BottomAppBar(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)),
        tonalElevation = BottomAppBarDefaults.ContainerElevation,
    ) {
        CustomButton(
            onCustomButtonClick = { onButtonClick() },
            buttonText = "Add To Bag",
            modifier = Modifier.fillMaxWidth()
        )
    }
}
