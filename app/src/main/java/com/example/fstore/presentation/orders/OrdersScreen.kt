package com.example.fstore.presentation.orders

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.fstore.presentation.orders.components.CustomOderCards
import com.example.fstore.presentation.orders.components.CustomOrderTopAppBar
import com.example.fstore.presentation.utils.CustomMultiColoredProgressBar
import com.example.fstore.presentation.utils.handle_paging.PagingState
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor


@Composable
fun OrdersScreen(
    modifier: Modifier = Modifier,
    viewModel: OrdersViewModel,
    onDetailsClick: (Int) -> Unit,
    onBackClick: () -> Unit
) {
    val orders = viewModel.state.value.orders?.collectAsLazyPagingItems()
    val pageState = orders?.let { handleOrdersPaging(orders = it) }

    Scaffold(
        modifier = modifier.background(BackgroundColor),
        topBar = { CustomOrderTopAppBar(onBackClick = { onBackClick() }) },
    ) {
        val pad = it.calculateTopPadding()
        if (pageState == PagingState.INITIAL_LOADING) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CustomMultiColoredProgressBar()
            }
        } else {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(top = pad)
            ) {
                if(orders!=null){
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(orders.itemCount) { index ->
                            orders[index]?.let { item ->
                                CustomOderCards(
                                    order = item,
                                    onDetailsClick = { id -> onDetailsClick(id) },
                                )
                            }
                        }
                        if (pageState == PagingState.NEW_DATA_LOADING) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(200.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CustomMultiColoredProgressBar()
                                }
                            }
                        }
                    }
                }
                if(pageState == PagingState.SUCCESS) {
                    if(orders.itemCount == 0)
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(
                                text = "You not made any orders",
                                color = ButtonColor,
                                fontSize = 26.sp
                            )
                        }
                }
            }
        }
    }
}