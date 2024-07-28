package com.example.fstore.presentation.favorite.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import com.example.fstore.domain.model.rec_sub.Favorite
import com.example.fstore.presentation.utils.CustomDressCardShimmerEffect
import com.example.fstore.presentation.utils.handle_paging.PagingState

@Composable
fun CustomFavoriteGridPaging(
    modifier: Modifier = Modifier,
    favorite: LazyPagingItems<Favorite>,
    onDeleteClick: (Int) -> Unit,
    onNav: (Int) -> Unit,
    onBagClick: (Favorite) -> Unit,
    pagingState: PagingState,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = modifier.fillMaxWidth()) {
        items(count = favorite.itemCount) { index ->
            favorite[index]?.let { favoriteItem ->
                CustomFavGridCard(favorite = favoriteItem,
                    onDeleteClick = { onDeleteClick(it) },
                    onNav = { onNav(it) },
                    onBagClick = { onBagClick(it) })
            }
        }
        if (pagingState == PagingState.NEW_DATA_LOADING) {
            item { CustomDressCardShimmerEffect(modifier = Modifier.padding(5.dp)) }
        }
        if (pagingState == PagingState.INITIAL_LOADING) {
            items(4) { CustomDressCardShimmerEffect(modifier = Modifier.padding(5.dp)) }
        }
    }
}
