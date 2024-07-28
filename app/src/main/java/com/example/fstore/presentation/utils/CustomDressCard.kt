package com.example.fstore.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.presentation.utils.components.CustomDiscountCard
import com.example.fstore.presentation.utils.components.CustomRatingStars
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.ui.theme.TextLighterShade
import com.example.fstore.utils.cap

@Composable
fun CustomDressCard(
    modifier: Modifier = Modifier,
    product: Product,
    onNav: (Int) -> Unit,
) {
    Box(modifier = modifier
        .width(170.dp)
        .clip(RoundedCornerShape(10.dp))
        .background(Color.White)
        .clickable { onNav(product.id) }) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                SubcomposeAsyncImage(
                    model = product.thumbnailUrl,
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
                        .padding(5.dp)
                        .fillMaxSize()
                        .clip(RoundedCornerShape(10.dp)),
                    contentScale = ContentScale.Crop
                )
                CustomDiscountCard(
                    discountPrice = product.discount,
                    modifier = Modifier.align(Alignment.TopStart),
                    isNew = product.new
                )
                CustomFavButton(
                    modifier = Modifier.align(Alignment.BottomEnd), isFav = product.isFav
                )
            }

            CustomRatingStars(totalRating = product.noOfRating, filledStar = product.rating)
            Text(
                text = product.name.uppercase(),
                color = TextLighterShade,
                overflow = TextOverflow.Clip,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
            )
            Text(
                text = product.category.cap(),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 15.sp,
                overflow = TextOverflow.Clip,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = modifier
                    .padding(end = 5.dp, bottom = 5.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "$${product.price}",
                    color = if (product.discount != 0) TextLighterShade else ButtonColor,
                    style = TextStyle(textDecoration = if (product.discount != 0) TextDecoration.LineThrough else TextDecoration.None),
                    fontSize = 17.sp,
                    overflow = TextOverflow.Clip,
                    fontWeight = if (product.discount != 0) FontWeight.Normal else FontWeight.Bold,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                )
                if (product.discount != 0) Text(
                    text = "$${product.dPrice}",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                )
            }
        }
    }
}
