package com.example.fstore.presentation.order_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fstore.domain.model.FetchOrderModel
import com.example.fstore.presentation.order_details.components.CustomOrderDetailTopAppBar
import com.example.fstore.presentation.order_details.components.CustomOrderDetailsItemSection
import com.example.fstore.presentation.order_details.components.CustomOrderDetailsTopSection
import com.example.fstore.presentation.order_details.components.CustomOrderInfoSection
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor

@Composable
fun OrderDetailsScreen(
    modifier: Modifier = Modifier, orderDetails: FetchOrderModel?, onBackClick: () -> Unit
) {
    val scroll = rememberScrollState()
    Scaffold(modifier = modifier.background(BackgroundColor),
        topBar = { CustomOrderDetailTopAppBar(onBackClick = { onBackClick() }) }) {
        val pad = it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(top = pad + 20.dp)
                .verticalScroll(scroll)
        ) {
            if (orderDetails != null) {
                CustomOrderDetailsTopSection(orderDetails = orderDetails)
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(420.dp)
                    .padding(start = 10.dp, end = 10.dp, top = 5.dp, bottom = 5.dp)
            ) {
                if (orderDetails != null) {
                    items(orderDetails.orderProducts.size) { index ->
                        val products = orderDetails.orderProducts[index]
                        CustomOrderDetailsItemSection(orderProduct = products)
                    }
                }
            }
            if (orderDetails != null) {
                CustomOrderInfoSection(
                    address = orderDetails.address.address, price = orderDetails.totalPrice
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(containerColor = ButtonColor)
                ) {
                    Text(text = "Leave feedback")
                }
            }
        }
    }
}