package com.example.fstore.presentation.order_details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.fstore.domain.model.rec_sub.OrderProduct
import com.example.fstore.presentation.utils.CustomMultiColoredProgressBar
import com.example.fstore.ui.theme.TextLighterShade
import com.example.fstore.utils.cap

@Composable
fun CustomOrderDetailsItemSection(modifier: Modifier = Modifier,orderProduct: OrderProduct) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .height(135.dp)
            .padding(top = 10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(
                Color.White
            )
    ) {
        Row {
            SubcomposeAsyncImage(
                model = orderProduct.thumbnailUrl,
                loading = {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomMultiColoredProgressBar()
                    }
                },
                contentDescription = "Product images",
                modifier = Modifier
                    .fillMaxHeight()
                    .width(110.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier.padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Column {
                    Text(
                        text = orderProduct.category.cap(),
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Text(
                        text = orderProduct.name.uppercase(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextLighterShade,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                    Row(modifier = Modifier.padding(start = 5.dp)) {
                        Text(
                            text = "Color : ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = TextLighterShade
                        )
                        Text(
                            text = orderProduct.color.cap(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = "Size : ",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = TextLighterShade
                        )
                        Text(
                            text = orderProduct.size.cap(),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Normal,
                            color = Color.Black
                        )
                    }
                }
//                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Units: ${orderProduct.quantity}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = TextLighterShade,
                    )
                    Text(
                        text = "$${orderProduct.price}",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}