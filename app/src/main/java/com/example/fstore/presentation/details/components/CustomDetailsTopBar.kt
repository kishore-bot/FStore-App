package com.example.fstore.presentation.details.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Share
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
import com.example.fstore.utils.cap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDetailsTopBar(modifier: Modifier = Modifier, onBackClick: () -> Unit, title: String) {
    CenterAlignedTopAppBar(
        modifier = modifier.height(80.dp),
        title = {
            Text(
                text = title.cap(),
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp)
            )
        },
        navigationIcon = {
            IconButton(onClick = { onBackClick.invoke() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = { /* Handle share click here */ }) {
                Icon(imageVector = Icons.Default.Share, contentDescription = null)
            }
        }
    )
}
