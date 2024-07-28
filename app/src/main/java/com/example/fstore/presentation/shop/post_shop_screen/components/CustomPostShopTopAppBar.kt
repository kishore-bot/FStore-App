package com.example.fstore.presentation.shop.post_shop_screen.components


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.domain.model.CategoryModel


@Composable
fun CustomPostShopTopAppBar(
    modifier: Modifier = Modifier,
    isList: Boolean,
    onListClick: () -> Unit,
    text: String,
    bottomSheetState: MutableState<Boolean>,
    selectedSortOption: MutableState<Int>,
    onFilterClick: () -> Unit,
    categories: CategoryModel,
    onCategoryClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onSearch: () -> Unit
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(onClick = { onBackClick.invoke() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
                Text(text = text, fontSize = 24.sp, fontWeight = FontWeight.Bold)
                IconButton(onClick = { onSearch.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(categories.size) { index ->
                    val item = categories[index]
                    Box(
                        modifier = Modifier
                            .height(30.dp)
                            .width(120.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.Black)
                            .clickable { onCategoryClick(item) },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = item.uppercase(),
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(modifier = modifier.clickable { onFilterClick.invoke() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.MenuOpen, contentDescription = null
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "Filters", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                CustomPostShopSortMenu(
                    onSortMenuClick = {
                        bottomSheetState.value = true
                    },
                    sortOptions = sortOptions,
                    bottomSheetState = bottomSheetState,
                    selectedSortOption = selectedSortOption
                )
                IconButton(onClick = { onListClick.invoke() }) {
                    Icon(
                        imageVector = if (!isList) Icons.Default.GridOn else Icons.AutoMirrored.Filled.List,
                        contentDescription = null,
                        modifier = Modifier.size(30.dp)
                    )
                }
            }
        }
    }
}
