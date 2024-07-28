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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor

@Composable
fun CustomFilterSizes(
    listItems: List<FilterSizes>,
    selectedSizes: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Sizes", fontSize = 20.sp, fontWeight = FontWeight.W600)
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
                    val isSelected = selectedSizes == item.id
                    Card(
                        shape = RectangleShape,
                        modifier = Modifier
                            .size(55.dp)
                            .clickable {
                                onItemSelected(if (isSelected) -1 else item.id)
                            },
                        colors = CardDefaults.cardColors(containerColor = if (isSelected) ButtonColor else BackgroundColor)
                    ) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = item.size.uppercase(),
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) Color.White else Color.Black
                            )
                        }
                    }
                }
            }
        }
    }
}


data class FilterSizes(
    val id: Int, val size: String,
)

val filterSizes = listOf(
    FilterSizes(0, "xm"),
    FilterSizes(1, "m"),
    FilterSizes(2, "l"),
    FilterSizes(3, "xl"),
    FilterSizes(4, "xxl"),
    FilterSizes(5, "xxxl"),
)