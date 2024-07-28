package com.example.fstore.presentation.checkout.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.fstore.domain.model.rec_sub.Addresses
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.utils.cap

@Composable
fun CustomCheckoutAddressCard(
    modifier: Modifier = Modifier, onAddressClick: () -> Unit, address: Addresses?
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = CardDefaults.elevatedCardElevation(3.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (address != null) address.address?.let {
                Text(
                    text = it.cap(), fontWeight = FontWeight.SemiBold
                )
            }
            TextButton(onClick = { onAddressClick.invoke() }) {
                Text(text = "Change", color = ButtonColor)
            }
        }
        if (address != null) {
            Text(
                text = "${address.address?.cap()} \n" + "${address.city?.cap()}, ${address.state?.cap()} ${address.pincode}, ${address.country?.cap()}",
                modifier = Modifier.padding(start = 10.dp, bottom = 10.dp),
                overflow = TextOverflow.Ellipsis
            )
        }
        if (address == null) {
            Text(
                text = "No Address Selected Yet",
                modifier = Modifier
                    .padding(start = 10.dp, bottom = 10.dp)
                    .align(Alignment.CenterHorizontally),
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}