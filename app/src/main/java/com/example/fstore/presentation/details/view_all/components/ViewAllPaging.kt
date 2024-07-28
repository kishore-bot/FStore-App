package com.example.fstore.presentation.details.view_all.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.presentation.utils.CustomListDressCard
import com.example.fstore.presentation.utils.CustomListDressCardShimmerEffect
import com.example.fstore.presentation.utils.handle_paging.PagingState

@Composable
fun ViewAllPaging(
    modifier: Modifier = Modifier,
    product: LazyPagingItems<Product>,
    onNav: (Int) -> Unit,
    pagingState: PagingState,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(count = product.itemCount) { index ->
            product[index]?.let { productItem ->
                CustomListDressCard(
                    product = productItem,
                    onNav = {
                        onNav(it)
                    },
                )
            }
        }
        if (pagingState == PagingState.NEW_DATA_LOADING) {
            item { CustomListDressCardShimmerEffect() }
        }
        if (pagingState == PagingState.INITIAL_LOADING) {
            items(4) { CustomListDressCardShimmerEffect() }
        }
    }
}