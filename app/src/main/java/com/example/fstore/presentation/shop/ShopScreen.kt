package com.example.fstore.presentation.shop

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fstore.R
import com.example.fstore.presentation.shop.components.CustomShopAppBar
import com.example.fstore.presentation.shop.components.CustomShopCard
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.utils.cap

@Composable
fun ShopScreen(modifier: Modifier = Modifier, onNav: (String, String) -> Unit,tabIndex:MutableState<Int>) {



    val tabs = listOf("female", "male", "kids")

    val pageItems =
        if (tabIndex.value == 0) listOfWomenPaging else if (tabIndex.value == 1) listOfMensPaging else listOfKidsPaging

    Scaffold(modifier = modifier.fillMaxSize(),
        containerColor = BackgroundColor,
        topBar = { CustomShopAppBar() }) { paddingValues ->
        val top = paddingValues.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = top)
        ) {
            TabRow(
                selectedTabIndex = tabIndex.value,
            ) {
                tabs.forEachIndexed { index, item ->
                    Tab(
                        selectedContentColor = Color.Black,
                        selected = tabIndex.value == index,
                        onClick = { tabIndex.value = index },
                        text = {
                            Text(
                                item.cap(),
                                fontSize = 20.sp,
                                fontWeight = if (tabIndex.value == index) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                    )
                }
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(pageItems.size) { index ->
                    CustomShopCard(
                        text = pageItems[index].string.cap(),
                        image = pageItems[index].image,
                        onClick = { onNav.invoke(tabs[tabIndex.value], pageItems[index].string) },
                    )
                }
            }
        }
    }
}

data class PageItems(
    val image: Int, val string: String
)

val listOfMensPaging = listOf(
    PageItems(R.drawable.men_clothes, string = "clothes"),
    PageItems(R.drawable.men_shoe, string = "shoes"),
    PageItems(R.drawable.men_accessories, string = "accessories"),
    PageItems(R.drawable.men_cosmetics, "cosmetics"),
)

val listOfWomenPaging = listOf(
    PageItems(R.drawable.women_clothes, string = "clothes"),
    PageItems(R.drawable.women_shoes, string = "shoes"),
    PageItems(R.drawable.women_accessories, string = "accessories"),
    PageItems(R.drawable.women_cosmetics, "cosmetics"),
)

val listOfKidsPaging = listOf(
    PageItems(R.drawable.kids_clothes, string = "clothes"),
    PageItems(R.drawable.kids_shoes, string = "shoes"),
    PageItems(R.drawable.kids_acceesories, string = "accessories"),
    PageItems(R.drawable.kids_cometics, "cosmetics"),
)