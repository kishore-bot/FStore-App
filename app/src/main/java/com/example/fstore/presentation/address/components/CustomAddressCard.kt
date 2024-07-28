package com.example.fstore.presentation.address.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
fun CustomAddressCard(
    modifier: Modifier = Modifier,
    addresses: Addresses,
    checked: Boolean,
    onCheckedChange: (Int) -> Unit,
    onDelete: (Int) -> Unit
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
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            addresses.name?.let { Text(text = it.cap(), fontWeight = FontWeight.SemiBold) }
            TextButton(onClick = { addresses.id?.let { onDelete(it) } }) {
                Text(text = "Delete", color = ButtonColor)
            }
        }
        Text(
            text = "${addresses.address?.cap()} \n" + "${addresses.city?.cap()}, ${addresses.state?.cap()} ${addresses.pincode}, ${addresses.country?.cap()}",
            modifier = Modifier.padding(start = 15.dp),
            overflow = TextOverflow.Ellipsis
        )
        Row(
            modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = checked,
                onCheckedChange = { addresses.id?.let { it1 -> onCheckedChange(it1) } },
                modifier = Modifier.padding(start = 10.dp)
            )
            Text(
                text = "Use this as shipping address",
            )
        }
    }
}