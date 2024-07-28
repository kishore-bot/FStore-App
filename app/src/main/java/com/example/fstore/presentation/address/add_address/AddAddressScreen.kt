package com.example.fstore.presentation.address.add_address

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.fstore.domain.model.send.CreateAddressModel
import com.example.fstore.presentation.address.components.CustomAddressTopAppbar
import com.example.fstore.presentation.utils.CustomButton
import com.example.fstore.presentation.utils.CustomTextField
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.utils.showToastMessage
import kotlinx.coroutines.delay

@Composable
fun AddAddressScreen(
    modifier: Modifier = Modifier, viewModel: AddAddressViewModel, onBackClick: () -> Unit
) {
    val activity = LocalContext.current as Activity
    var name by remember { mutableStateOf(TextFieldValue("")) }
    var address by remember { mutableStateOf(TextFieldValue("")) }
    var state by remember { mutableStateOf(TextFieldValue("")) }
    var country by remember { mutableStateOf(TextFieldValue("")) }
    var pincode by remember { mutableStateOf(TextFieldValue("")) }
    var city by remember { mutableStateOf(TextFieldValue("")) }

    val message = viewModel.state.value.message

    LaunchedEffect(message) {
        if (message != null) {
            if (message.message.isNotBlank()) {
                showToastMessage(message = message.message, activity = activity)
                delay(200)
                onBackClick.invoke()
            }
        }
    }

    Scaffold(
        modifier = modifier.background(BackgroundColor),
        topBar = {
            CustomAddressTopAppbar(
                text = "Adding Shipping Address",
                onBackClick = { onBackClick() },
            )
        },
    ) {
        val pad = it.calculateTopPadding()
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
                .padding(top = pad)
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            CustomTextField(
                value = name,
                onValueChange = { newTextFieldValue -> name = newTextFieldValue },
                labelText = "Full Name",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            CustomTextField(
                value = address,
                onValueChange = { newTextFieldValue -> address = newTextFieldValue },
                labelText = "Address",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            CustomTextField(
                value = city,
                onValueChange = { newTextFieldValue -> city = newTextFieldValue },
                labelText = "City",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            CustomTextField(
                value = state,
                onValueChange = { newTextFieldValue -> state = newTextFieldValue },
                labelText = "State/Province/Region",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            CustomTextField(
                value = pincode,
                onValueChange = { newTextFieldValue -> pincode = newTextFieldValue },
                labelText = "Pin Code",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            CustomTextField(
                value = country,
                onValueChange = { newTextFieldValue -> country = newTextFieldValue },
                labelText = "Country",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(20.dp))
            CustomButton(
                onCustomButtonClick = {
                    createAddress(
                        address = address.text,
                        city = city.text,
                        activity = activity,
                        name = name.text,
                        pincode = pincode.text.toInt(),
                        state = state.text,
                        country = country.text,
                        viewModel = viewModel
                    )

                }, buttonText = "Save Address", modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )
        }
    }
}

private fun createAddress(
    activity: Activity,
    viewModel: AddAddressViewModel,
    name: String,
    address: String,
    city: String,
    pincode: Int,
    state: String,
    country: String,
) {
    if (name.isBlank() || address.isBlank() || city.isBlank() || state.isBlank() || country.isBlank() || pincode > 999999 || pincode < 100000) {
        showToastMessage(message = "Error some field", activity = activity)
    } else {
        val addressModel = CreateAddressModel(
            name = name,
            address = address,
            city = city,
            pincode = pincode,
            country = country,
            state = state
        )
        viewModel.onEvent(AddAddressEvent.UpdateAddress(address = addressModel))
        viewModel.onEvent(AddAddressEvent.CreateAddress)
    }
}