package com.example.fstore.presentation.bag.componets

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.fstore.domain.model.rec_sub.Bag
import com.example.fstore.presentation.bag.paging.handleBagPaging
import com.example.fstore.presentation.favorite.components.CustomFavListCardShimmerEffect
import com.example.fstore.presentation.utils.handle_paging.PagingState

@Composable
fun CustomBagPaging(
    modifier: Modifier = Modifier,
    bags: LazyPagingItems<Bag>,
    onDelete: (Int) -> Unit,
    onFav: (Bag) -> Unit,
    pagingState: PagingState,
    navToDetailScreen: (Int) -> Unit,
    onChangeQuantity: (Bag) -> Unit,
) {
    LazyColumn(modifier = modifier) {
        items(count = bags.itemCount) { index ->
            bags[index]?.let { bagItems ->
                CustomBagCard(
                    bag = bagItems,
                    onDelete = { onDelete(it) },
                    onFav = { onFav(it) },
                    navToDetailScreen = { navToDetailScreen(it) },
                    onChangeQuantity = { onChangeQuantity(it) },
                )
            }
        }
        if (pagingState == PagingState.NEW_DATA_LOADING) {
            item { CustomFavListCardShimmerEffect() }
        }
        if (pagingState == PagingState.INITIAL_LOADING) {
            items(4) { CustomFavListCardShimmerEffect() }
        }
    }
}
