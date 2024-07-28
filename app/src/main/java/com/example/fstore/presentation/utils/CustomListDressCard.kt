package com.example.fstore.presentation.utils

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.StarRate
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import com.example.fstore.R
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.presentation.utils.components.CustomDiscountCard
import com.example.fstore.presentation.utils.components.CustomRatingStars
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.ui.theme.ShimmerColor
import com.example.fstore.utils.cap
import com.example.fstore.utils.shimmerEffect


@Composable
fun CustomListDressCard(
    modifier: Modifier = Modifier,
    product: Product,
    onNav: (Int) -> Unit,
) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(140.dp)
        .padding(10.dp)
        .clickable { onNav(product.id) }
        .clip(RoundedCornerShape(10.dp))
        .background(
            Color.White
        )) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(140.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Row {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(110.dp),
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
                            .fillMaxHeight()
                            .width(110.dp)
                            .clip(RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)),
                        contentScale = ContentScale.Crop
                    )
                    if (product.discount != 0 || product.new) CustomDiscountCard(
                        discountPrice = product.discount,
                        modifier = Modifier.align(Alignment.TopStart),
                        isNew = product.new,
                    )
                }
                Column(
                    modifier = Modifier.padding(start = 10.dp, top = 5.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.Start
                ) {
                    Column(modifier = Modifier.padding(start = 5.dp)) {
                        Text(
                            text = product.name.uppercase(),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Gray,
                        )
                        Text(
                            text = product.category.cap(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.height(5.dp))
                    CustomRatingStars(totalRating = product.noOfRating, filledStar = product.rating)
                    Spacer(modifier = Modifier.height(5.dp))
                    Row {
                        Text(
                            text = if (product.dPrice == 0) "$${product.price}" else "$${product.dPrice}",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = ButtonColor,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))

                    }
                }
            }
        }
        CustomFavButton(
            isFav = product.isFav,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-3).dp, y = 4.dp)
        )
    }

}


@Composable
fun CustomListDressCardShimmerEffect(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(150.dp)
            .padding(10.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(110.dp)
                    .shimmerEffect(),
            )
            Column(
                modifier = Modifier.padding(start = 10.dp, top = 5.dp, end = 5.dp)
            ) {
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(80.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .shimmerEffect(),
                )
                Box(
                    modifier = Modifier
                        .height(30.dp)
                        .width(80.dp)
                        .padding(top = 8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .shimmerEffect(),
                )

                for (i in 1..5) {
                    Icon(
                        imageVector = Icons.Filled.StarRate,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp),
                        tint = ShimmerColor
                    )
                }
                Box(
                    modifier = Modifier
                        .height(25.dp)
                        .width(50.dp)
                        .padding(end = 8.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .shimmerEffect(),
                )
            }
        }
        Icon(
            imageVector = Icons.Filled.Cancel,
            contentDescription = null,
            modifier = Modifier
                .size(20.dp)
                .align(Alignment.TopEnd)
                .offset(x = (-9f).dp, y = (3).dp),
            tint = ShimmerColor
        )
        Box(
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .shimmerEffect()
                .align(Alignment.BottomEnd)
                .offset(x = (-20).dp, y = (-30).dp)
        )
    }
}

