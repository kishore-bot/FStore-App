package com.example.fstore.presentation.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Label
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.fstore.presentation.profile.components.CustomProfileAppBar
import com.example.fstore.presentation.profile.components.CustomProfileButton
import com.example.fstore.presentation.profile.components.CustomProfileCard
import com.example.fstore.ui.theme.BackgroundColor

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier, onOrderClick: () -> Unit, onAddressClick: () -> Unit
) {
    val viewModel: ProfileViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        viewModel.fetchProfile()
    }
    val profile = viewModel.state.value.profileModel

    Scaffold(modifier = modifier.background(BackgroundColor), topBar = { CustomProfileAppBar() }) {
        val pad = it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = pad)
                .background(Color.Transparent)
        ) {
            if (profile != null) {
                CustomProfileCard(profileModel = profile)
            }
            Spacer(modifier = Modifier.height(30.dp))
            CustomProfileButton(mainText = "My Orders",
                subText = "Already have 12 orders",
                onclick = { onOrderClick() })
            CustomProfileButton(
                mainText = "Shipping Address",
                subText = "3 address",
                onclick = { onAddressClick() },
            )

            CustomProfileButton(mainText = "Payment methods", subText = "Visa ***22", onclick = {})
            CustomProfileButton(
                mainText = "Promo codes",
                subText = "You have special promo codes",
                onclick = {},
            )
            CustomProfileButton(
                mainText = "My reviews",
                subText = "Reviews for 4 items",
                onclick = {},
            )
            CustomProfileButton(
                mainText = "Settings",
                subText = "Notification password",
                onclick = {},
            )
        }
    }

}