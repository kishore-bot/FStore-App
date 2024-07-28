package com.example.fstore.presentation.navigator.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fstore.ui.theme.ButtonColor


@Composable
fun CustomBottomAppBar(
    modifier: Modifier = Modifier,
    items: List<BottomNavigationBarItems>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = modifier
            .height(90.dp)
            .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)),
        containerColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemClick(index) },
                icon = {
                    Icon(
                        imageVector = if (index == selectedIndex) item.selectedIcon else item.unSelectedIcon,
                        contentDescription = item.title,
                        modifier = Modifier.size(25.dp)
                    )
                },
                label = { Text(text = item.title) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent,
                    selectedIconColor = ButtonColor,
                    unselectedIconColor = Color.Gray.copy(alpha = 0.5f),
                    selectedTextColor = ButtonColor,
                    unselectedTextColor = Color.Gray.copy(alpha = 0.5f)
                )
            )
        }
    }
}