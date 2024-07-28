package com.example.fstore.presentation.home.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.domain.model.ProductModel
import com.example.fstore.presentation.utils.CustomDressCard

@Composable
fun HomeSection(
    modifier: Modifier = Modifier,
    products: ProductModel,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = "New Collection",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        }
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(products.products) { item ->
                CustomDressCard(
                    modifier = Modifier.padding(vertical = 8.dp),
                    product = item,
                    onNav = { }
                )
            }
        }
    }
}
