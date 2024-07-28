package com.example.fstore.presentation.bag

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.fstore.domain.model.rec_sub.Bag
import com.example.fstore.domain.model.send.CreateFavModel
import com.example.fstore.presentation.bag.componets.CustomBagAlertBox
import com.example.fstore.presentation.bag.componets.CustomBagPaging
import com.example.fstore.presentation.bag.componets.CustomBagTopAppBar
import com.example.fstore.presentation.bag.componets.CustomBagTotalPrice
import com.example.fstore.presentation.bag.paging.handleBagPaging
import com.example.fstore.presentation.utils.CustomButton
import com.example.fstore.presentation.utils.handle_paging.PagingState
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.utils.showToastMessage
import kotlinx.coroutines.delay

@Composable
fun BagScreen(
    modifier: Modifier = Modifier,
    viewModel: BagViewModel,
    navToDetailScreen: (Int) -> Unit,
    onSubmit: (Int) -> Unit
) {
    val context = LocalContext.current as Activity
    val bags = viewModel.state.value.bags?.collectAsLazyPagingItems()
    val price = viewModel.state.value.price?.collectAsState(initial = null)
    val message = viewModel.state.value.messages

    val shouldShowDialog = remember { mutableStateOf(false) }
    var selectedBag by remember { mutableStateOf<Bag?>(null) }

    LaunchedEffect(message) {
        if (message != null) {
            showToastMessage(message.message, context)
            delay(500)
            viewModel.onEvent(BagEvents.ClearMessage)
        }
    }

    val pagingState = bags?.let { handleBagPaging(bags = it) }

    Scaffold(
        containerColor = BackgroundColor,
        modifier = modifier.fillMaxSize(),
        topBar = { CustomBagTopAppBar() },
        bottomBar = {
            Column {
                price?.value?.let { CustomBagTotalPrice(price = it.price) }
                CustomButton(
                    onCustomButtonClick = {
                        if (bags?.itemCount != 0) {
                            if (price != null) {
                                price.value?.let { onSubmit(it.price) }
                            }
                        } else {
                            showToastMessage("No Products Found To CheckOut", context)
                        }
                    }, buttonText = "Checkout", modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
        },
    ) { paddingValues ->
        val top = paddingValues.calculateTopPadding()
        val bottom = paddingValues.calculateBottomPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(top = top)
        ) {
            bags?.let { bagItems ->
                pagingState?.let { state ->
                    CustomBagPaging(bags = bagItems,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = bottom),
                        onDelete = { id -> deleteBag(viewModel = viewModel, id = id) },
                        onFav = { bag -> postFavorite(viewModel = viewModel, bag = bag) },
                        pagingState = state,
                        navToDetailScreen = { id -> navToDetailScreen(id) },
                        onChangeQuantity = { bag ->
                            selectedBag = bag
                            shouldShowDialog.value = true
                        })
                }
            }
        }
        if (pagingState == PagingState.SUCCESS && bags.itemCount == 0) {
            Box(
                modifier = Modifier.fillMaxSize().padding(10.dp), contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "No Products Found",
                    fontSize = 26.sp,
                    color = ButtonColor,
                    fontWeight = FontWeight.W600
                )
            }
        }
    }

    selectedBag?.let { bag ->
        CustomBagAlertBox(shouldShowDialog = shouldShowDialog, bag = bag, onSave = { updatedBag ->
            viewModel.onEvent(BagEvents.UpdateQuantity(bag = updatedBag))
            viewModel.onEvent(BagEvents.UploadBag)
            shouldShowDialog.value = false
        }, onDismiss = {
            shouldShowDialog.value = false
        })
    }
}

private fun deleteBag(
    viewModel: BagViewModel, id: Int
) {
    viewModel.onEvent(BagEvents.UploadId(id))
    viewModel.onEvent(BagEvents.DeleteBag)
}

private fun postFavorite(
    viewModel: BagViewModel, bag: Bag
) {
    val createFavModel = CreateFavModel(
        id = bag.productId, color = bag.color, size = bag.size
    )
    viewModel.onEvent(BagEvents.UploadFav(createFavModel))
    viewModel.onEvent(BagEvents.CreateFav)
}
