package com.example.fstore.presentation.bag.componets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SwitchRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.example.fstore.domain.model.rec_sub.Bag
import com.example.fstore.presentation.utils.CustomMultiColoredProgressBar
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.ui.theme.TextLighterShade
import com.example.fstore.utils.cap

@Composable
fun CustomBagCard(
    modifier: Modifier = Modifier,
    bag: Bag,
    onDelete: (Int) -> Unit,
    onFav: (Bag) -> Unit,
    navToDetailScreen: (Int) -> Unit,
    onChangeQuantity: (Bag) -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(140.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            SubcomposeAsyncImage(
                model = bag.thumbnailUrl,
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
                    .clickable { navToDetailScreen(bag.productId) }
                    .width(100.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(10.dp)
            ) {
                Text(
                    text = bag.category.cap(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 5.dp)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row {
                    Text(
                        text = "Color : ",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextLighterShade
                    )
                    Text(
                        text = bag.color.cap(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Size : ",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextLighterShade
                    )
                    Text(
                        text = bag.size.uppercase(),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Quantity : ",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = TextLighterShade
                    )
                    Text(
                        text = "${bag.quantity}",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black,
                        modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                    )
                    IconButton(onClick = { onChangeQuantity(bag) }) {
                        Icon(
                            imageVector = Icons.Default.SwitchRight,
                            contentDescription = null,
                            tint = ButtonColor,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                }
            }

        }
        Box(
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.TopEnd)
        ) {
            CustomBagMenu(onFavClick = { onFav(bag) }, onDeleteClick = { onDelete(bag.id) })
        }

        Box(
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.BottomEnd)
        ) {
            Text(
                text = "$${bag.price}",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                modifier = Modifier.padding(start = 5.dp)
            )

        }
    }
}