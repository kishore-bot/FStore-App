package com.example.fstore.presentation.search.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.fstore.presentation.utils.CustomTextField
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor
import okhttp3.internal.wait

@Composable
fun CustomSearchTopAppBar(
    modifier: Modifier = Modifier, searchClick: (String) -> Unit, onBackClick: () -> Unit
) {
    val searchText = remember { mutableStateOf(TextFieldValue("")) }
    Card(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 25.dp, bottom = 20.dp, start = 10.dp, end = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = { onBackClick.invoke() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    tint = Color.Black
                )
            }
            CustomTextField(
                modifier = Modifier.width(280.dp),
                value = searchText.value,
                onValueChange = { new -> searchText.value = new },
                labelText = "Search",
            )
            Card(
                modifier = Modifier.size(40.dp),
                onClick = { searchClick(searchText.value.text) },
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = ButtonColor)

            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        tint = Color.White
                    )
                }

            }
        }
    }
}