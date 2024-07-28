package com.example.fstore.presentation.checkout.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.R
import com.example.fstore.ui.theme.ButtonColor

@Composable
fun CustomCheckoutDeliveryCard(modifier: Modifier = Modifier) {
    var selected by remember {
        mutableIntStateOf(1)
    }
    Text(
        text = "Delivery method",
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
        modifier = Modifier.padding(start = 10.dp, top = 20.dp)
    )
    val deliveryOptions = listOf(
        R.drawable.fedx, R.drawable.usps, R.drawable.dhl
    )

    Row(
        modifier = modifier
            .padding(10.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        for (deliveryOption in deliveryOptions) {
            val isSelected = (selected == deliveryOption)
            CustomCheckoutSupportCard(
                image = deliveryOption,
                isSelected = isSelected,
                onSelect = {
                    selected = deliveryOption
                },
            )
        }
    }

}

@Composable
fun CustomCheckoutSupportCard(
    modifier: Modifier = Modifier, image: Int, isSelected: Boolean = false, onSelect: () -> Unit
) {
    Card(
        onClick = { onSelect.invoke() },
        modifier = modifier
            .padding(end = 10.dp)
            .width(100.dp)
            .height(90.dp),
        border = BorderStroke(
            width = 5.dp, color = if (isSelected) ButtonColor else Color.Transparent
        ),
        elevation = CardDefaults.elevatedCardElevation(3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = "Card",
                    modifier = Modifier.size(60.dp),
                    contentScale = ContentScale.FillWidth
                )
                Text(text = "1-2 days", color = Color.Gray, fontSize = 13.sp)
            }
        },
    )
}