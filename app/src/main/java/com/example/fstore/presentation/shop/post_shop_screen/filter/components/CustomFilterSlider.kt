package com.example.fstore.presentation.shop.post_shop_screen.filter.components


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.RangeSlider
import androidx.compose.material3.SliderDefaults
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
import com.example.fstore.ui.theme.ButtonColor

@Composable
fun CustomFilterSlider(
    modifier: Modifier = Modifier, onValueChange: (Int, Int) -> Unit, low: Float, high: Float
) {
    var sliderPosition by remember { mutableStateOf(low..high) }

    val lowValue = sliderPosition.start.toInt()
    val highValue = sliderPosition.endInclusive.toInt()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(text = "Price Range", fontSize = 20.sp, fontWeight = FontWeight.W600)
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(Color.White), contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$$lowValue", fontSize = 16.sp, fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(start = 30.dp, top = 20.dp),
                    )
                    Text(
                        text = "$$highValue", fontSize = 16.sp, fontWeight = FontWeight.W600,
                        modifier = Modifier.padding(end = 30.dp, top = 20.dp),
                    )
                }
                RangeSlider(
                    modifier = Modifier.padding(15.dp),
                    colors = SliderDefaults.colors(
                        thumbColor = ButtonColor,
                        activeTrackColor = ButtonColor,
                        inactiveTickColor = Color.Gray
                    ),
                    value = sliderPosition,
                    steps = 500,
                    onValueChange = { range -> sliderPosition = range },
                    valueRange = 0f..1000f,
                    onValueChangeFinished = {
                        onValueChange(lowValue, highValue)
                    },
                )
            }
        }
    }
}