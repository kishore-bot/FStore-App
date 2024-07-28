package com.example.fstore.presentation.order_details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.R

@Composable
fun CustomOrderInfoSection(modifier: Modifier = Modifier, address: String, price: Int) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(text = "Order information", fontWeight = FontWeight.Bold, fontSize = 20.sp)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Shipping Address: ", fontWeight = FontWeight.Light, color = Color.Gray)
            Text(
                text =address, fontWeight = FontWeight.Bold
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Payment method: ", fontWeight = FontWeight.Light, color = Color.Gray)
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.master),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "**** **** **** 1234",
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Delivery method: ", fontWeight = FontWeight.Light, color = Color.Gray)
            Text(text = "FedEx, 3 days, 15$", fontWeight = FontWeight.Bold)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Total amount: ", fontWeight = FontWeight.Light, color = Color.Gray)
            Text(
                text = "$$price", fontWeight = FontWeight.Bold, fontSize = 26.sp
            )
        }

    }

}