package com.example.fstore.presentation.favorite

import android.app.Activity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.fstore.domain.model.rec_sub.Favorite
import com.example.fstore.domain.model.send.CreateBagModel
import com.example.fstore.presentation.favorite.components.CustomFavTopBar
import com.example.fstore.presentation.favorite.components.CustomFavoriteGridPaging
import com.example.fstore.presentation.favorite.components.CustomFavoriteListCardPaging
import com.example.fstore.presentation.favorite.paging.handleFavoritePaging
import com.example.fstore.presentation.utils.handle_paging.PagingState
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.utils.showToastMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    onNav: (Int) -> Unit,
    viewModel: FavoriteViewModel,
    onSearch: () -> Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current as Activity
    val favorites = viewModel.state.value.favorites?.collectAsLazyPagingItems()

    var initialLoad by remember {
        mutableStateOf(true)
    }

    LaunchedEffect(initialLoad) {
        if (initialLoad) {
            scope.launch {
                viewModel.onEvent(FavoriteEvents.FetchFavorites)
                delay(1000)
                initialLoad = false
            }
        }
    }


    var list by remember {
        mutableStateOf(true)
    }

    val messages = viewModel.state.value.messages


    LaunchedEffect(messages) {
        if (messages != null) {
            showToastMessage(messages.message, context)
            viewModel.onEvent(FavoriteEvents.ClearMessage)
        }
    }
    val pagingState = favorites?.let { handleFavoritePaging(favorites = it) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CustomFavTopBar(
                onListClick = { list = !list },
                isList = list,
                context = context,
                onSearch = { onSearch() },
            )
        },
    ) {
        val top = it.calculateTopPadding()
        if (favorites != null) {
            if (pagingState != null) {
                if (list) {
                    CustomFavoriteListCardPaging(
                        modifier = Modifier.padding(
                            bottom = 20.dp, top = top + 10.dp
                        ),
                        favorite = favorites,
                        onDeleteClick = { id ->
                            deleteFavorite(viewModel = viewModel, id = id)
                        },
                        onNav = { id -> onNav(id) },
                        onBagClick = { favorite ->
                            createBag(
                                favorite = favorite, viewModel = viewModel
                            )
                        },
                        pagingState = pagingState,
                    )
                } else {
                    CustomFavoriteGridPaging(
                        modifier = Modifier.padding(
                            bottom = 20.dp, top = top + 10.dp
                        ),
                        favorite = favorites,
                        onDeleteClick = { id ->
                            deleteFavorite(viewModel = viewModel, id = id)
                        },
                        onNav = { id -> onNav(id) },
                        onBagClick = { favorite ->
                            createBag(favorite = favorite, viewModel = viewModel)
                        },
                        pagingState = pagingState,
                    )

                }
            }
        }
        if (pagingState == PagingState.SUCCESS) {
            if (favorites.itemCount == 0) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = top),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Add Favorite Products",
                        fontSize = 26.sp,
                        color = ButtonColor,
                        fontWeight = FontWeight.W600
                    )
                }
            }
        }
    }
}

private fun deleteFavorite(
    viewModel: FavoriteViewModel, id: Int
) {
    viewModel.onEvent(FavoriteEvents.UploadId(id))
    viewModel.onEvent(FavoriteEvents.DeleteFavorite)
}

private fun createBag(
    viewModel: FavoriteViewModel, favorite: Favorite
) {
    val size = favorite.size
    val price = if (favorite.dPrice != 0) favorite.dPrice else favorite.price
    val color = favorite.color
    val id = favorite.id
    val bag = CreateBagModel(
        productId = id, color = color, size = size, price = price
    )
    viewModel.onEvent(FavoriteEvents.UploadBag(bag))
    viewModel.onEvent(FavoriteEvents.CreateBag)

}