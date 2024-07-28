package com.example.fstore.presentation.orders.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fstore.domain.model.rec_sub.Order

@Composable
fun CustomOderCards(modifier: Modifier = Modifier, order: Order, onDetailsClick: (Int) -> Unit) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ), colors = CardDefaults.cardColors(
            containerColor = Color.White
        ), modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Order No:${order.orderId.uppercase()}", fontWeight = FontWeight.Bold)
            Text(
                text = order.createdAt.substring(0, 9),
                fontWeight = FontWeight.Light,
                color = Color.Gray
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "Track No: ", fontWeight = FontWeight.Light, color = Color.Gray)
            Text(text = order.orderNo, fontWeight = FontWeight.Bold)
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                Text(text = "Quantity: ", fontWeight = FontWeight.Light, color = Color.Gray)
                Text(text = order.quantity.toString(), fontWeight = FontWeight.Bold)

            }
            Row {
                Text(text = "Amount: ", fontWeight = FontWeight.Light, color = Color.Gray)
                Text(text = "$${order.totalPrice}", fontWeight = FontWeight.Bold)
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(onClick = { onDetailsClick.invoke(order.id) }) {
                Text(text = "Details")
            }
            Text(text = "Delivered", fontWeight = FontWeight.Bold, color = Color.Green)
        }
    }
}
