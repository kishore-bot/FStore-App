package com.example.fstore.presentation.bag.componets

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.domain.model.rec_sub.Bag
import com.example.fstore.presentation.utils.CustomButton

@Composable
fun CustomBagAlertBox(
    shouldShowDialog: MutableState<Boolean>,
    bag: Bag,
    onSave: (Bag) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    if (shouldShowDialog.value) {
        var quantity by remember { mutableIntStateOf(bag.quantity) }

        AlertDialog(modifier = modifier,
            onDismissRequest = { onDismiss() },
            title = { Text(text = "Edit Bag Quantity") },
            text = {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(35.dp),
                        onClick = { if (quantity > 1) quantity -= 1 },
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.elevatedCardElevation(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Remove,
                            contentDescription = "Add",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(5.dp)
                        )
                    }
                    Text(
                        text = quantity.toString(),
                        fontSize = 26.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                    Card(
                        modifier = Modifier.size(35.dp),
                        onClick = { quantity += 1 },
                        shape = CircleShape,
                        colors = CardDefaults.cardColors(Color.White),
                        elevation = CardDefaults.elevatedCardElevation(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            modifier = Modifier
                                .size(40.dp)
                                .padding(5.dp)
                        )
                    }
                }
            },
            confirmButton = {
                CustomButton(onCustomButtonClick = {
                    onSave(bag.copy(quantity = quantity))
                }, buttonText = "Save")
            },
            dismissButton = {
                CustomButton(onCustomButtonClick = { onDismiss() }, buttonText = "Dismiss")
            })
    }
}
