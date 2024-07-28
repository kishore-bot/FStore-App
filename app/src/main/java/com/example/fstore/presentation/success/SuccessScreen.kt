package com.example.fstore.presentation.success

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fstore.R
import com.example.fstore.presentation.success.components.CustomSuccessSection
import com.example.fstore.presentation.utils.CustomButton

@Composable
fun SuccessScreen(modifier: Modifier = Modifier, onContinueShoppingClick: () -> Unit) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.success),
            contentDescription = "Success",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        CustomSuccessSection(modifier = Modifier.offset(x = 20.dp, y = 130.dp))
        CustomButton(
            onCustomButtonClick = { onContinueShoppingClick.invoke() },
            buttonText = "Continue Shopping",
            modifier = Modifier
                .width(300.dp)
                .offset(x = 50.dp, y = 230.dp)
        )
    }
}