package com.example.fstore.presentation.profile.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomProfileAppBar(modifier: Modifier = Modifier) {
    MediumTopAppBar(
        modifier =  modifier,
        title = {
        Text(
            text = "My Profile",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        )
    })
}