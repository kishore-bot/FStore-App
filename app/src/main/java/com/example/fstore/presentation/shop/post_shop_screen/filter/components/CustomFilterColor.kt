package com.example.fstore.presentation.shop.post_shop_screen.filter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor



@Composable
fun CustomFilterColor(
    listItems: List<FilterColors>,
    modifier: Modifier = Modifier,
    selectedColors: Int,
    onColorSelected: (Int) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Colors", fontSize = 20.sp, fontWeight = FontWeight.W600)
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White)
                .clip(RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(listItems) { item ->
                    val isSelected = item.id == selectedColors
                    Card(
                        shape = CircleShape,
                        modifier = Modifier
                            .size(60.dp)
                            .clickable {
                                onColorSelected(if (isSelected) -1 else item.id)
                            },
                        colors = CardDefaults.cardColors(containerColor = if (isSelected) ButtonColor else BackgroundColor)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .clip(CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(item.color)
                            )
                        }
                    }
                }
            }
        }
    }
}

data class FilterColors(
    val id: Int, val colorName: String, val color: Color
)

val filterColorList = listOf(
    FilterColors(0, "Black", Color(0xFF000000)),
    FilterColors(1, "White", Color(0xFFFFFFFF)),
    FilterColors(2, "Gray", Color(0xFF808080)),
    FilterColors(3, "Navy Blue", Color(0xFF000080)),
    FilterColors(4, "Red", Color(0xFFFF0000)),
    FilterColors(5, "Blue", Color(0xFF0000FF)),
    FilterColors(6, "Green", Color(0xFF008000)),
    FilterColors(7, "Yellow", Color(0xFFFFFF00)),
    FilterColors(8, "Pink", Color(0xFFFFC0CB)),
    FilterColors(9, "Brown", Color(0xFFA52A2A)),
    FilterColors(10, "Beige", Color(0xFFF5F5DC)),
    FilterColors(11, "Purple", Color(0xFF800080)),
    FilterColors(12, "Orange", Color(0xFFFFA500)),
    FilterColors(13, "Teal", Color(0xFF008080))
)
