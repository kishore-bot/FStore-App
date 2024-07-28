package com.example.fstore.presentation.favorite.components

import android.app.Activity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.MenuOpen
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.utils.showToastMessage


@Composable
fun CustomFavTopBar(
    modifier: Modifier = Modifier,
    isList: Boolean,
    onListClick: () -> Unit,
    context: Activity,
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
                Text(text = "Favorites", fontSize = 30.sp, fontWeight = FontWeight.Bold)
                IconButton(
                    onClick = {
                        onSearch()
                    },
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null,
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = {
                            showToastMessage(
                                "filter is not defined yet", activity = context
                            )
                        },
                        modifier = Modifier.padding(end = 5.dp),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.MenuOpen,
                            contentDescription = null,
                            modifier = Modifier.size(25.dp)
                        )
                    }
                    Text(text = "Filters", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = {
                            showToastMessage(
                                "Sorting is not defined yet", activity = context
                            )
                        },
                    ) {
                        Row {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.Sort,
                                contentDescription = null,
                                modifier = Modifier.size(25.dp)
                            )
                        }
                    }
                    Text(text = "Sort Options", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                }
                IconButton(onClick = { onListClick() }) {
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