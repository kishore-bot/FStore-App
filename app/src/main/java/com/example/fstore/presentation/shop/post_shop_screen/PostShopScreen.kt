package com.example.fstore.presentation.shop.post_shop_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.LazyPagingItems
import com.example.fstore.domain.model.CategoryModel
import com.example.fstore.domain.model.rec_sub.Product
import com.example.fstore.domain.model.send.ProductQuery
import com.example.fstore.presentation.shop.ShopEvent
import com.example.fstore.presentation.shop.ShopViewModel
import com.example.fstore.presentation.shop.post_shop_screen.components.CustomGridPaging
import com.example.fstore.presentation.shop.post_shop_screen.components.CustomListPaging
import com.example.fstore.presentation.shop.post_shop_screen.components.CustomPostShopTopAppBar
import com.example.fstore.presentation.utils.handle_paging.PagingState
import com.example.fstore.presentation.utils.handle_paging.handleProductPagingResult
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.utils.cap

@Composable
fun PostShopScreen(
    modifier: Modifier = Modifier,
    viewModel: ShopViewModel,
    gender: String,
    onFilterClick: () -> Unit,
    onBackClick: () -> Unit,
    products: LazyPagingItems<Product>?,
    category: State<CategoryModel?>?,
    onNav: (Int) -> Unit,
    onSearch: () -> Unit
) {

    val pagingState = products?.let { handleProductPagingResult(product = it) }

    var isList by remember { mutableStateOf(true) }
    val isBottomSheetVisible = remember { mutableStateOf(false) }
    val selectedSortOption = remember { mutableIntStateOf(-1) }

    LaunchedEffect(selectedSortOption.intValue) {
        changeSortedOption(viewModel = viewModel, index = selectedSortOption.intValue)
    }



    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            if (category != null) {
                category.value?.let {
                    CustomPostShopTopAppBar(
                        isList = isList,
                        onListClick = { isList = !isList },
                        text = "${gender.cap()}'s",
                        bottomSheetState = isBottomSheetVisible,
                        selectedSortOption = selectedSortOption,
                        onFilterClick = { onFilterClick.invoke() },
                        categories = it,
                        onCategoryClick = { category ->
                            updateCategory(
                                viewModel = viewModel, category = category
                            )
                        },
                        onBackClick = { onBackClick() }, onSearch = { onSearch() },
                    )
                }
            }
        },
    ) {
        val topPaddingValues = it.calculateTopPadding()
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = topPaddingValues + 10.dp)
        ) {
            if (products != null) {
                if (pagingState != null) {
                    if (isList) {
                        CustomListPaging(
                            product = products,
                            onNav = { id -> onNav(id) },
                            pagingState = pagingState
                        )
                    } else {
                        CustomGridPaging(product = products,
                            pagingState = pagingState,
                            onNav = { id -> onNav(id) })
                    }
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

private fun updateCategory(viewModel: ShopViewModel, category: String) {

    val currentQuery = viewModel.state.value.query ?: ProductQuery()
    val updatedQuery = currentQuery.copy(category = category)
    viewModel.onEvent(ShopEvent.UploadQuery(query = updatedQuery))
    viewModel.onEvent(ShopEvent.FetchProducts)
    return
}

private fun changeSortedOption(viewModel: ShopViewModel, index: Int) {
    val currentQuery = viewModel.state.value.query ?: ProductQuery()
    if (index == 5) {
        val updatedQuery = ProductQuery(
            mainCategory = currentQuery.mainCategory, gender = currentQuery.gender
        )
        viewModel.onEvent(ShopEvent.UploadQuery(query = updatedQuery))
        viewModel.onEvent(ShopEvent.FetchProducts)
        return
    }
    if (index == 6) {
        return
    }
    val updatedQuery = currentQuery.copy(
        byPriceSort = when (index) {
            3 -> 1
            4 -> 2
            else -> 0
        },
        byNew = if (index == 1) 1 else 0,
        byRating = if (index == 2) 1 else 0,
        byPopular = if (index == 0) 1 else 0
    )

    viewModel.onEvent(ShopEvent.UploadQuery(updatedQuery))
    viewModel.onEvent(ShopEvent.FetchProducts)
}