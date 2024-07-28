package com.example.fstore.presentation.utils


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fstore.ui.theme.ShimmerColor
import com.example.fstore.utils.shimmerEffect

@Composable
fun CustomDressCardShimmerEffect(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(170.dp)
            .height(300.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = modifier
                    .padding(end = 5.dp, bottom = 5.dp)
                    .fillMaxWidth()

            ) {
                for (i in 1..5) {
                    Icon(
                        imageVector = Icons.Filled.StarRate,
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = ShimmerColor
                    )
                }
            }
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .width(130.dp)
                    .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )
            Box(
                modifier = Modifier
                    .height(20.dp)
                    .width(80.dp)
                    .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .shimmerEffect()
            )
            Row {
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(50.dp)
                        .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .shimmerEffect()
                )
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(50.dp)
                        .padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .shimmerEffect()
                )
            }
        }
    }
}
