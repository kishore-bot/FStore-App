package com.example.fstore.presentation.shop.post_shop_screen.filter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.presentation.utils.CustomButton

@Composable
fun CustomFilterBottomButton(
    modifier: Modifier = Modifier, onDiscard: () -> Unit, onApply: () -> Unit
) {
    BottomAppBar(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(
                onClick = { onDiscard.invoke() },
                modifier = Modifier.width(180.dp),
                colors = ButtonDefaults.outlinedButtonColors()
            ) {
                Text(
                    text = "Discard",
                    color = Color.Black,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(7.dp)
                )
            }
            CustomButton(
                onCustomButtonClick = { onApply.invoke() },
                buttonText = "Apply",
                modifier = Modifier.width(180.dp)
            )
        }
    }
}