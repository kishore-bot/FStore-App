package com.example.fstore.presentation.favorite.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.SubcomposeAsyncImage
import com.example.fstore.R
import com.example.fstore.domain.model.rec_sub.Favorite
import com.example.fstore.presentation.utils.CustomBagButton
import com.example.fstore.presentation.utils.CustomMultiColoredProgressBar
import com.example.fstore.presentation.utils.components.CustomDiscountCard
import com.example.fstore.presentation.utils.components.CustomRatingStars
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.ui.theme.TextLighterShade
import com.example.fstore.utils.cap

@Composable
fun CustomFavGridCard(
    modifier: Modifier = Modifier,
    favorite: Favorite,
    onDeleteClick: (Int) -> Unit,
    onNav: (Int) -> Unit,
    onBagClick: (Favorite) -> Unit
) {
    Box(modifier = modifier
        .width(170.dp)
        .clickable { onNav(favorite.id) }
        .clip(RoundedCornerShape(10.dp))
        .background(if (favorite.available) Color.White else BackgroundColor)) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                SubcomposeAsyncImage(
                    model = favorite.thumbnailUrl,
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
                CustomCloseButton(modifier = Modifier.align(Alignment.TopEnd)) {
                    onDeleteClick(favorite.id)
                }
                if (favorite.discount != 0 || favorite.new) CustomDiscountCard(
                    discountPrice = favorite.discount,
                    modifier = Modifier.align(Alignment.TopStart),
                    isNew = favorite.new,
                )
                if (favorite.available) CustomBagButton(
                    onBagClick = { onBagClick(favorite) },
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .offset(x = 1.5.dp, y = 7.dp)
                )
                if (!favorite.available) Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.Gray.copy(alpha = .7f))
                        .align(Alignment.BottomStart)
                        .offset(x = 1.5.dp, y = (-7).dp),
                    contentAlignment = Alignment.BottomStart,
                ) {
                    Text(
                        text = "Sorry, this item is currently sold out",
                        overflow = TextOverflow.Clip,
                        modifier = Modifier.padding(5.dp)
                    )
                }
            }
            CustomRatingStars(totalRating = favorite.noOfRating, filledStar = favorite.rating)
            Text(
                text = favorite.name.uppercase(),
                color = TextLighterShade,
                overflow = TextOverflow.Clip,
                modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
            )
            Text(
                text = favorite.category.cap(),
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
                    text = "$${favorite.price}",
                    color = if (favorite.discount != 0) TextLighterShade else ButtonColor,
                    style = TextStyle(textDecoration = if (favorite.discount != 0) TextDecoration.LineThrough else TextDecoration.None),
                    fontSize = 15.sp,
                    overflow = TextOverflow.Clip,
                    fontWeight = if (favorite.discount != 0) FontWeight.Normal else FontWeight.Bold,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                )
                if (favorite.discount != 0) Text(
                    text = "$${favorite.dPrice}",
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontSize = 15.sp,
                    overflow = TextOverflow.Clip,
                    modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                )
            }
        }
    }
}
