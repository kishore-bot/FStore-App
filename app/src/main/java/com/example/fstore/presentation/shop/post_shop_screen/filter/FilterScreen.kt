package com.example.fstore.presentation.shop.post_shop_screen.filter

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.fstore.domain.model.send.ProductQuery
import com.example.fstore.presentation.shop.ShopEvent
import com.example.fstore.presentation.shop.ShopViewModel
import com.example.fstore.presentation.shop.post_shop_screen.filter.components.CustomBrandButton
import com.example.fstore.presentation.shop.post_shop_screen.filter.components.CustomFilterBottomButton
import com.example.fstore.presentation.shop.post_shop_screen.filter.components.CustomFilterColor
import com.example.fstore.presentation.shop.post_shop_screen.filter.components.CustomFilterGender
import com.example.fstore.presentation.shop.post_shop_screen.filter.components.CustomFilterSizes
import com.example.fstore.presentation.shop.post_shop_screen.filter.components.CustomFilterSlider
import com.example.fstore.presentation.shop.post_shop_screen.filter.components.CustomFilterTopAppBar
import com.example.fstore.presentation.shop.post_shop_screen.filter.components.filterColorList
import com.example.fstore.presentation.shop.post_shop_screen.filter.components.filterGender
import com.example.fstore.presentation.shop.post_shop_screen.filter.components.filterSizes
import com.example.fstore.ui.theme.BackgroundColor

@Composable
fun FilterScreen(
    modifier: Modifier = Modifier,
    viewModel: ShopViewModel,
    onBackClick: () -> Unit,
    onBrandClick: () -> Unit
) {
    val currentQuery = viewModel.state.value.query ?: ProductQuery()

    val scrollState = rememberScrollState()
    var selectedColors by remember { mutableIntStateOf(-1) }
    var selectedSizes by remember { mutableIntStateOf(-1) }
    var selectedGender by remember { mutableIntStateOf(0) }
    var priceRange by remember { mutableStateOf(-1 to 1001) }

    selectedColors = filterColorList.find { it.colorName == currentQuery.color }?.id ?: -1
    selectedSizes = filterSizes.find { it.size == currentQuery.size }?.id ?: -1
    selectedGender = filterGender.find {
        it.gender == currentQuery.gender
    }?.id ?: 0
    priceRange = if (currentQuery.lowPrice != null && currentQuery.highPrice != null) {
        currentQuery.lowPrice to currentQuery.highPrice
    } else {
        0 to 10001
    }

    Log.d(
        "FilterTest", currentQuery.lowPrice.toString() + "  " + currentQuery.highPrice.toString()
    )

    Scaffold(
        containerColor = BackgroundColor, modifier = modifier.fillMaxSize(),
        topBar = {
            CustomFilterTopAppBar {
                onBackClick.invoke()
            }
        },
        bottomBar = {
            CustomFilterBottomButton(
                onDiscard = {
                    selectedGender = -1
                    selectedColors = -1
                    selectedSizes = -1
                    priceRange = -1 to 1001
                    val updatedQuery = ProductQuery(
                        mainCategory = currentQuery.mainCategory
                    )
                    viewModel.onEvent(ShopEvent.ClearQuery)
                    viewModel.onEvent(ShopEvent.UploadQuery(updatedQuery))
                    onBackClick.invoke()
                },
                onApply = {
                    val updatedQuery = currentQuery.copy(
                        lowPrice = if (priceRange.first == -1) null else priceRange.first,
                        highPrice = if (priceRange.second == 1001) null else priceRange.second,
                        color = if (selectedColors == -1) null else filterColorList[selectedColors].colorName,
                        size = if (selectedSizes == -1) null else filterSizes[selectedSizes].size,
                        gender = if (selectedGender == 0) null else filterGender[selectedGender].gender
                    )
                    viewModel.onEvent(ShopEvent.UploadQuery(query = updatedQuery))
                    onBackClick.invoke()
                },
            )
        },
    ) {
        val top = it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(top = top + 20.dp)
        ) {
            CustomFilterSlider(onValueChange = { low, high ->
                priceRange = low to high
            }, low = priceRange.first.toFloat(), high = priceRange.second.toFloat())
            CustomFilterColor(listItems = filterColorList,
                selectedColors = selectedColors,
                onColorSelected = { newSelectedColors ->
                    selectedColors = newSelectedColors
                })
            CustomFilterSizes(listItems = filterSizes,
                selectedSizes = selectedSizes,
                onItemSelected = { newSelectedSizes ->
                    selectedSizes = newSelectedSizes
                })
            CustomFilterGender(listItems = filterGender,
                selectedGender = selectedGender,
                onItemSelected = { newSelectedGender ->
                    selectedGender = newSelectedGender
                })
            CustomBrandButton(onBrandClick = { onBrandClick() })
            Spacer(modifier = Modifier.height(100.dp))
        }
    }
}
