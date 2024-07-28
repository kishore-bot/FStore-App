package com.example.fstore.presentation.order_details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fstore.domain.model.FetchOrderModel

@Composable
fun CustomOrderDetailsTopSection(modifier: Modifier = Modifier,orderDetails: FetchOrderModel) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp,)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Order No:${orderDetails.orderNo}", fontWeight = FontWeight.Bold)
            Text(text = orderDetails.createdAt.substring(0, 9), fontWeight = FontWeight.Light, color = Color.Gray)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(text = "Track Id: ", fontWeight = FontWeight.Light, color = Color.Gray)
                Text(text =  orderDetails.orderId.uppercase(), fontWeight = FontWeight.Bold)
            }
            Text(text = "Delivered", fontWeight = FontWeight.Bold, color = Color.Green)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = "${orderDetails.quantity} items ", fontWeight = FontWeight.SemiBold, color = Color.Gray)
            }
        }
    }
}