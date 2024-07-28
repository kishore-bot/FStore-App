package com.example.fstore.presentation.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.presentation.utils.CustomDressCard
import com.example.fstore.presentation.utils.CustomDressCardShimmerEffect
import com.example.fstore.presentation.utils.CustomTextButton
import com.example.fstore.presentation.utils.handle_paging.PagingState
import com.example.fstore.presentation.utils.handle_paging.handleProductPagingResult
import com.example.fstore.ui.theme.TextLighterShade


@Composable
fun CustomDetailsSimilarProducts(
    modifier: Modifier = Modifier, product: LazyPagingItems<Product>, onSimilar: () -> Unit,
) {
    val pagingState = handleProductPagingResult(product = product)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            Text(text = "Similar Products", fontSize = 20.sp, fontWeight = FontWeight.Bold)
            CustomTextButton(
                onCustomTextButtonClick = { onSimilar.invoke() },
                text = "View all ${product.itemCount} items",
                textColor = TextLighterShade,
                fSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(10.dp))

        LazyRow {
            items(count = product.itemCount) { index ->
                product[index]?.let { productItem ->
                    CustomDressCard(
                        modifier = Modifier.padding(5.dp),
                        product = productItem,
                        onNav = {},
                    )
                }
            }
            if (pagingState == PagingState.NEW_DATA_LOADING) {
                item {
                    CustomDressCardShimmerEffect(modifier = Modifier.padding(5.dp))
                }
            }
            if (pagingState == PagingState.INITIAL_LOADING) {
                items(4) {
                    CustomDressCardShimmerEffect(modifier = Modifier.padding(5.dp))
                }
            }
        }
    }
}