package com.example.fstore.presentation.utils

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fstore.ui.theme.BackgroundColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAppBar(modifier: Modifier = Modifier, onBackClick: () -> Unit) {
    TopAppBar(
        modifier = modifier,
        title = {},
        navigationIcon = {
            IconButton(onClick = { onBackClick() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back Button",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        colors = topAppBarColors(BackgroundColor),
    )
}