package com.example.fstore.presentation.checkout

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.presentation.checkout.components.CustomCheckoutAddressCard
import com.example.fstore.presentation.checkout.components.CustomCheckoutAmountSection
import com.example.fstore.presentation.checkout.components.CustomCheckoutDeliveryCard
import com.example.fstore.presentation.checkout.components.CustomCheckoutPaymentSection
import com.example.fstore.presentation.checkout.components.CustomCheckoutTopAppBar
import com.example.fstore.presentation.utils.CustomButton
import com.example.fstore.presentation.utils.CustomMultiColoredProgressBar
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.utils.showToastMessage

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    onAddressClick: () -> Unit,
    onBackClick: () -> Unit,
    onSubmit: () -> Unit,
    amount: String,
    viewModel: CheckOutViewModel
) {
    val activity = LocalContext.current as Activity
    LaunchedEffect(Unit) {
        viewModel.fetchPrimAryAddress()
    }
    val messageModel = viewModel.state.value.messageModel

    var loading by remember { mutableStateOf(false) }
    LaunchedEffect(messageModel) {
        if (messageModel != null) {
            showToastMessage(messageModel.message, activity)
            loading = false
            onSubmit.invoke()
        }
    }
    val address = viewModel.state.value.address
    Scaffold(modifier = modifier.background(BackgroundColor),
        topBar = { CustomCheckoutTopAppBar(onBackClick = { onBackClick() }) }) {
        val pad = it.calculateTopPadding()
        if (loading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CustomMultiColoredProgressBar()
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent)
                    .padding(top = pad + 20.dp),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Shipping Address",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 10.dp, top = 20.dp)
                    )
                    CustomCheckoutAddressCard(
                        onAddressClick = { onAddressClick() }, address = address
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomCheckoutPaymentSection()
                    Spacer(modifier = Modifier.height(20.dp))
                    CustomCheckoutDeliveryCard()
                    Spacer(modifier = Modifier.height(10.dp))
                    CustomCheckoutAmountSection(amount = amount)
                    Spacer(modifier = Modifier.height(10.dp))
                }
                CustomButton(
                    onCustomButtonClick = {
                        if (address != null) {
                            loading = true
                            viewModel.placeOrder()
                        } else showToastMessage(
                            "Please Add Address", activity = activity
                        )
                    },
                    buttonText = "Submit Order",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                )
            }
        }
    }
}