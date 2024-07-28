package com.example.fstore.presentation.details.view_all

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.fstore.presentation.details.DetailsViewModel
import com.example.fstore.presentation.details.view_all.components.CustomViewAllTopScreen
import com.example.fstore.presentation.details.view_all.components.ViewAllPaging
import com.example.fstore.presentation.utils.handle_paging.handleProductPagingResult
import com.example.fstore.ui.theme.BackgroundColor

@Composable
fun ViewAllScreen(
    modifier: Modifier = Modifier,
    onNav: (Int) -> Unit,
    onBack: () -> Unit,
    viewModel: DetailsViewModel
) {
    val products = viewModel.state.value.products?.collectAsLazyPagingItems()
    val pagingState = products?.let { handleProductPagingResult(product = it) }
    Scaffold(modifier = modifier.fillMaxSize(),
        containerColor = BackgroundColor,
        topBar = { CustomViewAllTopScreen(onBackClick = { onBack.invoke() }) }) {
        val topPadding = it.calculateTopPadding()
        if (pagingState != null) {
            ViewAllPaging(
                modifier = Modifier.padding(top = topPadding + 15.dp),
                pagingState = pagingState,
                onNav = { id -> onNav(id) },
                product = products
            )
        }
    }
}