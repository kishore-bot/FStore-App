package com.example.fstore.presentation.shop.post_shop_screen.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.presentation.utils.CustomDressCard
import com.example.fstore.presentation.utils.CustomDressCardShimmerEffect
import com.example.fstore.presentation.utils.CustomListDressCard
import com.example.fstore.presentation.utils.handle_paging.PagingState

@Composable
fun CustomGridPaging(
    modifier: Modifier = Modifier,
    product: LazyPagingItems<Product>,
    onNav: (Int) -> Unit,
    pagingState: PagingState,
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), modifier = modifier
            .fillMaxSize()
    ) {
        items(count = product.itemCount) { index ->
            product[index]?.let { productItem ->
                CustomDressCard(
                    product = productItem,
                    onNav = {
                        onNav(it)
                    },
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
    Spacer(modifier = Modifier.height(80.dp))
}
