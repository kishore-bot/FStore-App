package com.example.fstore.presentation.checkout.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.R
import com.example.fstore.ui.theme.ButtonColor


@Composable
fun CustomCheckoutPaymentSection(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween

    ) {
        Text(
            text = "Payment",
            fontWeight = FontWeight.W500,
            fontSize = 20.sp,
        )
        TextButton(onClick = { /*TODO*/ }) {
            Text(text = "Change", color = ButtonColor)
        }
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.master),
            contentDescription = "Card",
            modifier = Modifier.size(30.dp),
            contentScale = ContentScale.Fit
        )
        Text(text = "**** **** **** 1234", modifier = modifier.padding(start = 20.dp))
    }
}