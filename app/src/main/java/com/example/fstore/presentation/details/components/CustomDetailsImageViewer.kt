package com.example.fstore.presentation.details.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.SubcomposeAsyncImage
import com.example.fstore.R
import com.example.fstore.presentation.utils.CustomMultiColoredProgressBar

@Composable
fun CustomDetailsImageViewer(modifier: Modifier = Modifier,images:List<String>) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(415.dp)
            .padding(5.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        items(images.size) {index->

            SubcomposeAsyncImage(
                model = images[index],
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
                    .width(275.dp)
                    .padding(end = 5.dp),
                contentScale = ContentScale.Crop
            )
//            Image(
//                painter = painterResource(id = R.drawable.b),
//                contentDescription = null,
//
//                contentScale = ContentScale.Crop
//            )
        }
    }
}