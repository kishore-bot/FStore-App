package com.example.fstore.presentation.bag.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.ui.theme.TextLighterShade

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomBagMenu(
    onFavClick: () -> Unit, onDeleteClick: () -> Unit, modifier: Modifier = Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded, onExpandedChange = { expanded = !expanded }, modifier = modifier
    ) {
        Icon(
            imageVector = Icons.Default.MoreVert,
            contentDescription = "More Menu",
            Modifier
                .size(25.dp)
                .menuAnchor(),
            tint = TextLighterShade
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .width(130.dp)
                .background(color = Color.White)
        ) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Add to favorites", fontSize = 14.sp, color = TextLighterShade
                    )
                },
                contentPadding = PaddingValues(5.dp),
                onClick = {
                    expanded = false
                    onFavClick.invoke()
                },
            )
            DropdownMenuItem(
                text = {
                    Text(
                        text = "Delete from the list", fontSize = 14.sp, color = TextLighterShade
                    )
                },
                contentPadding = PaddingValues(start = 5.dp, end = 5.dp, bottom = 5.dp),
                onClick = {
                    expanded = false
                    onDeleteClick.invoke()
                },
            )
        }
    }
}