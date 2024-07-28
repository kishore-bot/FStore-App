package com.example.fstore.presentation.address

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.fstore.presentation.address.components.CustomAddressCard
import com.example.fstore.presentation.address.components.CustomAddressFAB
import com.example.fstore.presentation.address.components.CustomAddressTopAppbar
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.utils.showToastMessage


@Composable
fun AddressScreen(
    modifier: Modifier = Modifier,
    onAddAddressClick: () -> Unit,
    onBackClick: () -> Unit,
    viewModel: AddressViewModel
) {
    val activity = LocalContext.current as Activity
    LaunchedEffect(Unit) {
        viewModel.onEvent(AddressEvent.FetchAddress)
    }
    val addressModel = viewModel.state.value.addressesModel
    var checked by remember { mutableIntStateOf(-1) }
    if (addressModel != null) {
        checked = addressModel.primaryAddress
    }

    var message = viewModel.state.value.messageModel

    if (message != null) {
        LaunchedEffect(true) {
            showToastMessage(message!!.message, activity)
            message = null
        }
    }

    Scaffold(modifier = modifier.background(BackgroundColor), topBar = {
        CustomAddressTopAppbar(text = "Shipping Addresses", onBackClick = { onBackClick() })
    }, floatingActionButton = {
        CustomAddressFAB(onAddAddressClick = { onAddAddressClick() })
    }) {
        val pad = it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(top = pad)
        ) {
            if (addressModel != null && addressModel.addresses.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp)
                ) {
                    items(addressModel.addresses.size) { index ->
                        val address = addressModel.addresses[index]
                        CustomAddressCard(
                            addresses = address,
                            checked = checked == address.id,
                            onCheckedChange = { id ->
                                if (checked != id) {
                                    viewModel.onEvent(AddressEvent.UploadId(id = id))
                                    checked = id
                                    viewModel.onEvent(AddressEvent.ChangeAddress)
                                }
                            },
                            onDelete = { id ->
                                viewModel.onEvent(AddressEvent.UploadId(id = id))
                                viewModel.onEvent(AddressEvent.DeletedAddress)
                            },
                        )
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    Text(
                        text = "No addresses found.",
                        color = ButtonColor
                    )
                }
            }
        }
    }
}
