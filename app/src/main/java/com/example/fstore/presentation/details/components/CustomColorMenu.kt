package com.example.fstore.presentation.details.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.ui.theme.DetailsScreenColor
import com.example.fstore.utils.cap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomColorMenu(
    selectedValue: String,
    options: List<String>,
    onValueChangedEvent: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = {}, modifier = modifier
    ) {
        Box(
            modifier = modifier
                .height(40.dp)
                .background(DetailsScreenColor)
                .clickable { expanded = !expanded  }
                .width(180.dp)
                .border(
                    width = 0.5.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)
                )
                .clip(RoundedCornerShape(10.dp))
                .padding(8.dp)
                .menuAnchor()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = selectedValue.cap(), fontSize = 15.sp, fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.width(50.dp))
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            }
        }

        ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
            options.forEachIndexed { index, option ->
                DropdownMenuItem(text = {
                    Text(text = option.cap(), fontSize = 18.sp)
                }, onClick = {
                    expanded = false
                    onValueChangedEvent(index)
                })
            }
        }
    }
}