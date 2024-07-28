package com.example.fstore.presentation.shop.post_shop_screen.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.ui.theme.ButtonColor


data class SortOptions(
    val sort: String
)

val sortOptions = listOf(
    SortOptions("Popular"),
    SortOptions("Newest"),
    SortOptions("Customer rating"),
    SortOptions("Price: lowest to high"),
    SortOptions("Price: highest to low"),
    SortOptions("Clear all filters"),
    SortOptions("None"),
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomPostShopSortMenu(
    modifier: Modifier = Modifier,
    onSortMenuClick: () -> Unit,
    sortOptions: List<SortOptions>,
    bottomSheetState: MutableState<Boolean>,
    selectedSortOption: MutableState<Int>
) {
    val sheetState = rememberModalBottomSheetState()
    Row(modifier = modifier.clickable { onSortMenuClick.invoke() }) {
        Icon(imageVector = Icons.AutoMirrored.Filled.Sort, contentDescription = null)
        Spacer(modifier = Modifier.width(5.dp))
        Text(text = "Sort Options", fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
    if (bottomSheetState.value) {
        ModalBottomSheet(
            onDismissRequest = { bottomSheetState.value = false },
            modifier = modifier,
            sheetState = sheetState
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Sort by", fontSize = 20.sp, fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(20.dp))

            CustomSortChart(
                chart = sortOptions, onSizeSelect = {
                    selectedSortOption.value = it
                    bottomSheetState.value = false
                }, selectedSortOption = selectedSortOption.value
            )
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}

@Composable
fun CustomSortChart(
    modifier: Modifier = Modifier,
    chart: List<SortOptions>,
    onSizeSelect: (Int) -> Unit,
    selectedSortOption: Int
) {
    LazyColumn(modifier = modifier, verticalArrangement = Arrangement.spacedBy(10.dp)) {
        itemsIndexed(chart) { index, item ->
            if (index == selectedSortOption) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ButtonColor)
                ) {
                    Text(
                        text = item.sort,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            } else {
                Text(
                    text = item.sort,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSizeSelect(index)
                        }
                        .padding(start = 10.dp, end = 10.dp, bottom = 10.dp),
                )
            }
        }
    }
}
