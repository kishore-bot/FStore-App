package com.example.fstore.presentation.shop.post_shop_screen.filter.brand

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fstore.domain.model.send.ProductQuery
import com.example.fstore.presentation.shop.ShopEvent
import com.example.fstore.presentation.shop.ShopViewModel
import com.example.fstore.presentation.shop.post_shop_screen.filter.brand.components.CustomBrandTopAppBar
import com.example.fstore.presentation.shop.post_shop_screen.filter.components.CustomFilterBottomButton
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor

@Composable
fun BrandScreen(modifier: Modifier = Modifier, viewModel: ShopViewModel, onBackClick: () -> Unit) {
    val brands = viewModel.state.value.brands?.collectAsState(initial = emptyList())
    val currentQuery = viewModel.state.value.query ?: ProductQuery()
    var selectedItems by remember { mutableStateOf<List<Int>>(emptyList()) }
    var selectedNames by remember { mutableStateOf<List<String>>(emptyList()) }


    Scaffold(containerColor = BackgroundColor, modifier = modifier.fillMaxSize(), topBar = {
        CustomBrandTopAppBar {
            onBackClick.invoke()
        }
    }, bottomBar = {
        CustomFilterBottomButton(onApply = {
            val updatedQuery = currentQuery.copy(name = selectedNames)
            viewModel.onEvent(ShopEvent.UploadQuery(updatedQuery))
            onBackClick.invoke()
        }, onDiscard = {
            val updatedQuery = currentQuery.copy(name = null)
            viewModel.onEvent(ShopEvent.UploadQuery(updatedQuery))
            selectedNames = emptyList()
            selectedItems = emptyList()
            onBackClick.invoke()
        })
    }) {
        val top = it.calculateTopPadding()
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = top + 20.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            brands?.value?.let { brandsList ->
                items(brandsList.size) { index ->
                    val item = brandsList[index]
                    val isSelected = selectedItems.contains(item.id)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = item.name.uppercase(),
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) ButtonColor else Color.Black
                        )
                        Box(modifier = Modifier
                            .size(30.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .border(
                                border = BorderStroke(
                                    width = 1.dp, color = Color.Black
                                ),
                                shape = RoundedCornerShape(10.dp),
                            )
                            .clickable {
                                if (isSelected) {
                                    selectedItems = selectedItems - item.id
                                    selectedNames =
                                        selectedNames.filterNot { id -> id == item.name }
                                } else {
                                    selectedItems = selectedItems + item.id
                                    selectedNames = selectedNames + item.name
                                }
                            }
                            .background(if (isSelected) ButtonColor else Color.White),
                            contentAlignment = Alignment.Center) {
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
