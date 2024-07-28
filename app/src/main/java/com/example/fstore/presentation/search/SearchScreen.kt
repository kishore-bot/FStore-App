package com.example.fstore.presentation.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.fstore.presentation.search.components.CustomSearchTopAppBar
import com.example.fstore.presentation.shop.post_shop_screen.components.CustomListPaging
import com.example.fstore.presentation.utils.handle_paging.PagingState
import com.example.fstore.presentation.utils.handle_paging.handleProductPagingResult
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor

@Composable
fun SearchScreen(modifier: Modifier = Modifier, onBackClick: () -> Unit, onNav: (Int) -> Unit) {
    val viewModel: SearchViewModel = hiltViewModel()
    val products = viewModel.state.value.products?.collectAsLazyPagingItems()

    val pagingState = products?.let { handleProductPagingResult(product = it) }
    Scaffold(
        containerColor = BackgroundColor, topBar = {
            CustomSearchTopAppBar(
                onBackClick = { onBackClick() },
                searchClick = { searchString ->
                    viewModel.onEvent(SearchEvent.UploadSearchQuery(searchString))
                    viewModel.onEvent(SearchEvent.Search)
                },
            )
        }, modifier = modifier.fillMaxSize()
    ) {
        val topPaddingValues = it.calculateTopPadding()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPaddingValues + 10.dp)
        ) {
            if (products != null) {
                if (pagingState != null) {
                    CustomListPaging(
                        product = products, onNav = { id -> onNav(id) }, pagingState = pagingState
                    )
                }
            }
            if (pagingState == PagingState.SUCCESS) {
                if (products.itemCount == 0) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(top = topPaddingValues),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Sorry No Product Found",
                            fontSize = 26.sp,
                            color = ButtonColor,
                            fontWeight = FontWeight.W600
                        )
                    }
                }
            }
        }
    }
}