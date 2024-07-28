package com.example.fstore.presentation.favorite.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import com.example.fstore.domain.model.rec_sub.Favorite
import com.example.fstore.presentation.utils.handle_paging.PagingState

@Composable
fun CustomFavoriteListCardPaging(
    modifier: Modifier = Modifier,
    favorite: LazyPagingItems<Favorite>,
    onDeleteClick: (Int) -> Unit,
    onNav: (Int) -> Unit,
    pagingState: PagingState,
    onBagClick: (Favorite) -> Unit
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(count = favorite.itemCount) { index ->
            favorite[index]?.let { favoriteItem ->
                CustomFavListCard(favorite = favoriteItem,
                    onDeleteClick = { onDeleteClick(it) },
                    onNav = { onNav(it) },
                    onBagClick = { onBagClick(it) })
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














//@Composable
//fun CustomFavoriteListCardPaging(
//    modifier: Modifier = Modifier,
//    favorite: LazyPagingItems<Favorite>,
//    onDeleteClick: (Int) -> Unit,
//    onNav: (Int) -> Unit,
//    onBagClick: (Favorite) -> Unit
//) {
//    val pagingState = handleFavoritePaging(favorite)
//
//    LazyColumn(modifier = modifier.fillMaxSize()) {
//        items(count = favorite.itemCount) { index ->
//            favorite[index]?.let { favoriteItem ->
//                CustomFavListCard(favorite = favoriteItem,
//                    onDeleteClick = { onDeleteClick(it) },
//                    onNav = { onNav(it) },
//                    onBagClick = { onBagClick(it) })
//            }
//        }
//        if (pagingState == PagingState.NEW_DATA_LOADING) {
//            item { CustomFavListCardShimmerEffect() }
//        }
//        if (pagingState == PagingState.INITIAL_LOADING) {
//            items(4) { CustomFavListCardShimmerEffect() }
//        }
//    }
//}