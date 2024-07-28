package com.example.fstore.presentation.navigator.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationBarItems(
    val title: String, val selectedIcon: ImageVector, val unSelectedIcon: ImageVector
)

val items = listOf(
    BottomNavigationBarItems(
        title = "Home",
        selectedIcon = Icons.Filled.Home,
        unSelectedIcon = Icons.Outlined.Home,
    ),
    BottomNavigationBarItems(
        title = "Shop",
        selectedIcon = Icons.Filled.ShoppingCart,
        unSelectedIcon = Icons.Outlined.ShoppingCart
    ),
    BottomNavigationBarItems(
        title = "Favorite",
        selectedIcon = Icons.Filled.Favorite,
        unSelectedIcon = Icons.Outlined.FavoriteBorder
    ),
    BottomNavigationBarItems(
        title = "Bag",
        selectedIcon = Icons.Filled.ShoppingBag,
        unSelectedIcon = Icons.Outlined.ShoppingBag
    ),
    BottomNavigationBarItems(
        title = "Profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unSelectedIcon = Icons.Outlined.AccountCircle
    ),
)