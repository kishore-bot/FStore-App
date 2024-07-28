package com.example.fstore.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fstore.ui.theme.BackgroundColor

@Composable
fun CustomSocialButton(modifier: Modifier = Modifier, icon: Int) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .height(100.dp)
            .width(140.dp)
            .background(Color.White)
            ,
        contentAlignment = Alignment.Center,

        ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = "Social Button",
            modifier = Modifier.size(50.dp),
            contentScale = ContentScale.Fit
        )
    }
}