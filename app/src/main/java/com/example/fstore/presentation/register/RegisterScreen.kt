package com.example.fstore.presentation.register

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.R
import com.example.fstore.domain.model.AuthModel
import com.example.fstore.presentation.utils.CustomButton
import com.example.fstore.presentation.utils.CustomPassWordField
import com.example.fstore.presentation.utils.CustomSocialButton
import com.example.fstore.presentation.utils.CustomTextButton
import com.example.fstore.presentation.utils.CustomTextField
import com.example.fstore.presentation.utils.isValidEmail
import com.example.fstore.presentation.utils.isValidPassword
import com.example.fstore.ui.theme.BackgroundColor
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.utils.showToastMessage
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel,
    onSuccess: () -> Unit,
    modifier: Modifier = Modifier,
    onLogInClick: () -> Unit,
) {

    val activity = LocalContext.current as Activity
    val result by viewModel.result.observeAsState(false)

    LaunchedEffect(result) {
        if (result) {
            showToastMessage(message = "Success", activity = activity)
            delay(200)
            onSuccess.invoke()
        }
    }

    Scaffold(
        modifier = modifier.fillMaxSize()
    ) {

        var nameTextValue by remember { mutableStateOf(TextFieldValue("")) }
        var emailTextValue by remember { mutableStateOf(TextFieldValue("")) }
        var passwordTextValue by remember { mutableStateOf(TextFieldValue("")) }
        val showPassword = remember { mutableStateOf(false) }

        val topPadding = it.calculateTopPadding()

        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .padding(top = topPadding)
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            Text(
                text = "Register",
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(start = 14.dp, top = 50.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))

            CustomTextField(
                value = nameTextValue, onValueChange = { newTextFieldValue ->
                    nameTextValue = newTextFieldValue
                }, labelText = "Name", modifier = Modifier
                    .padding(14.dp)
                    .fillMaxWidth()
            )
            CustomTextField(
                value = emailTextValue, onValueChange = { newTextFieldValue ->
                    emailTextValue = newTextFieldValue
                }, labelText = "Email", modifier = Modifier
                    .padding(14.dp)
                    .fillMaxWidth()
            )
            CustomPassWordField(
                value = passwordTextValue,
                onValueChange = { newTextFieldValue -> passwordTextValue = newTextFieldValue },
                showPassword = showPassword.value,
                changePassword = { password ->
                    showPassword.value = password
                },
                modifier = Modifier
                    .padding(14.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomTextButton(
                    onCustomTextButtonClick = { onLogInClick() }, text = "Already have an account?"
                )
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                    contentDescription = "Null",
                    tint = ButtonColor,
                    modifier = Modifier.size(25.dp)
                )
                Spacer(modifier = Modifier.width(15.dp))
            }
            Spacer(modifier = Modifier.height(10.dp))
            CustomButton(
                onCustomButtonClick = {
                    register(
                        name = nameTextValue.text,
                        email = emailTextValue.text,
                        password = passwordTextValue.text,
                        viewModel = viewModel,
                        activity = activity
                    )
                }, buttonText = "Register", modifier = Modifier
                    .padding(14.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Or sign up with social account")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomSocialButton(icon = R.drawable.google)
                CustomSocialButton(icon = R.drawable.fb)
            }
        }
    }
}

private fun register(
    name: String,
    email: String,
    password: String,
    activity: Activity,
    viewModel: RegisterViewModel,

    ) {
    if (email.isEmpty()) {
        showToastMessage(message = "Email should should be empty", activity = activity)
    } else if (!isValidEmail(email)) {
        showToastMessage(message = "Invalid Email Address", activity = activity)
    } else if (password == "") {
        showToastMessage(message = "Password should not be null", activity = activity)
    } else if(password.length<8 || isValidPassword(password)){
        showToastMessage(message = "Password should be valid", activity = activity)
    }
    else if (name == "") {
        showToastMessage(message = "Name should should not be null", activity = activity)
    } else {
        val authModel = AuthModel(
            name = name,
            email = email,
            password = password,
        )
        viewModel.onEvent(RegisterEvent.UpdateAuthModel(authModel = authModel))
        viewModel.onEvent(RegisterEvent.Register)
    }
}