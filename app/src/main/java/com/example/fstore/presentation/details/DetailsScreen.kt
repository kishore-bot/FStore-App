package com.example.fstore.presentation.details


import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.fstore.domain.model.DetailsModel
import com.example.fstore.domain.model.send.CreateBagModel
import com.example.fstore.domain.model.send.CreateFavModel
import com.example.fstore.presentation.details.components.CustomColorMenu
import com.example.fstore.presentation.details.components.CustomDetailFavButton
import com.example.fstore.presentation.details.components.CustomDetailsBottomBar
import com.example.fstore.presentation.details.components.CustomDetailsCard
import com.example.fstore.presentation.details.components.CustomDetailsImageViewer
import com.example.fstore.presentation.details.components.CustomDetailsSimilarProducts
import com.example.fstore.presentation.details.components.CustomDetailsSupportCard
import com.example.fstore.presentation.details.components.CustomDetailsTopBar
import com.example.fstore.presentation.details.components.CustomSizeScreen
import com.example.fstore.presentation.utils.CustomMultiColoredProgressBar
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.ui.theme.DetailsScreenColor
import com.example.fstore.utils.showToastMessage
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel,
    onBackClick: () -> Unit,
    onSimilar: () -> Unit,
    details: DetailsModel?,
    onReviewClick:(Int)->Unit
) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current as Activity

    val similarProducts = viewModel.state.value.products?.collectAsLazyPagingItems()
    var isLoading by remember { mutableStateOf(true) }
    var isBag by remember { mutableStateOf(false) }
    var isFavLoading by remember { mutableStateOf(false) }

    LaunchedEffect(details) {
        details?.let {
            scope.launch {
                delay(1000)
                isLoading = false
            }
        }
    }

    val sheetState = rememberModalBottomSheetState()
    val isBottomSheetVisible = remember { mutableStateOf(false) }
    val selectedSizeIndex = remember { mutableIntStateOf(-1) }
    var selectedColor by remember { mutableIntStateOf(0) }

    val message = viewModel.state.value.messageModel

    LaunchedEffect(message) {
        if (message != null) {
            showToastMessage(message.message, context)
            delay(800)
            isFavLoading = false
            isBag = false
            viewModel.onEvent(DetailsEvent.ClearMessage)
        }
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
            CustomMultiColoredProgressBar()
        }
    } else {
        Scaffold(
            modifier = modifier.fillMaxSize(),
            topBar = {
                CustomDetailsTopBar(
                    onBackClick = { onBackClick() }, title = details?.category ?: ""
                )
            },
            bottomBar = {
                if (!isBag && !isFavLoading) {
                    CustomDetailsBottomBar(onButtonClick = {
                        details?.let {
                            if (!it.available) showToastMessage(
                                "Currently Product Not Available", activity = context
                            )
                            else {
                                isBag = true
                                createBag(
                                    viewModel = viewModel,
                                    detailsModel = it,
                                    selectedSize = selectedSizeIndex.intValue,
                                    selectedColor = selectedColor
                                )
                            }
                        }
                    })
                }
            },
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .background(DetailsScreenColor)
                ) {
                    item {
                        if (details != null) {
                            CustomDetailsImageViewer(images = details.imageUrls)
                        }
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            details?.let { detail ->
                                CustomSizeScreen(
                                    onBoxClick = { isBottomSheetVisible.value = true },
                                    sheetState = sheetState,
                                    bottomSheetState = isBottomSheetVisible,
                                    size = detail.size,
                                    selectedSizeIndex = selectedSizeIndex
                                )
                                CustomColorMenu(selectedValue = detail.color[selectedColor],
                                    options = detail.color,
                                    onValueChangedEvent = { selectedColor = it })
                                CustomDetailFavButton(
                                    onFavClick = {
                                        details.let {
                                            isFavLoading = true
                                            postFavorite(
                                                viewModel = viewModel,
                                                selectedColor = selectedColor,
                                                selectedSize = selectedSizeIndex.intValue,
                                                detailsModel = it
                                            )
                                            it.favorite = true
                                        }
                                    }, isFav = detail.favorite
                                )
                            }
                        }
                    }
                    item {
                        if (details?.available == false) {
                            Text(
                                text = "Currently Product Not Available",
                                color = ButtonColor,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    }
                    item {
                        details?.let { CustomDetailsCard(details = it) }
                    }
                    item {
                        Column(modifier = Modifier.fillMaxWidth()) {
                            Spacer(modifier = Modifier.height(80.dp))
                            CustomDetailsSupportCard(text = "Shopping Info", onclick = {})
                            CustomDetailsSupportCard(text = "Support", onclick = {})
                        }
                    }
                    item {
                        similarProducts?.let {
                            CustomDetailsSimilarProducts(product = it, onSimilar = { onSimilar() })
                        }
                    }
                    item {Spacer(modifier = Modifier.height(20.dp))
                        CustomDetailsSupportCard(text = "Review of product", onclick = {
                            if (details != null) {
                                onReviewClick(details.id)
                            }
                        }, color = ButtonColor)
                        Spacer(modifier = Modifier.height(50.dp))
                    }
                }
                if (isFavLoading || isBag) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Transparent)
                            .clickable(enabled = false, onClick = {})
                            .align(Alignment.Center),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomMultiColoredProgressBar()
                    }
                }
            }
        }
    }
}

private fun postFavorite(
    viewModel: DetailsViewModel, selectedSize: Int, selectedColor: Int, detailsModel: DetailsModel
) {
    val size = if (selectedSize == -1) 0 else selectedSize
    val createFavModel = CreateFavModel(
        id = detailsModel.id,
        color = detailsModel.color[selectedColor],
        size = detailsModel.size[size]
    )
    viewModel.onEvent(DetailsEvent.UpdateFavoriteModel(createFavModel))
    viewModel.onEvent(DetailsEvent.PostFavorite)
}

private fun createBag(
    viewModel: DetailsViewModel, selectedSize: Int, selectedColor: Int, detailsModel: DetailsModel
) {
    val size = if (selectedSize == -1) detailsModel.size[0] else detailsModel.size[selectedSize]
    val price = detailsModel.price
    val color = detailsModel.color[selectedColor]
    val id = detailsModel.id
    val bag = CreateBagModel(
        productId = id, color = color, size = size, price = price.toInt()
    )
    viewModel.onEvent(DetailsEvent.UploadBag(bag))
    viewModel.onEvent(DetailsEvent.CreateBag)
}
