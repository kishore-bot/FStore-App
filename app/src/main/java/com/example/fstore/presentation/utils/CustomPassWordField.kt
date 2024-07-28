package com.example.fstore.presentation.utils

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun CustomPassWordField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    showPassword: Boolean,
    changePassword: (Boolean) -> Unit,
    onValueChange: (TextFieldValue) -> Unit,
) {

    TextField(
        value = value,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        modifier = modifier
            .height(64.dp)
            .clip(RoundedCornerShape(10.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedIndicatorColor = Color.White
        ),
        label = { Text(text = "Password", fontWeight = FontWeight.Bold) },
        visualTransformation = if (showPassword) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            IconButton(onClick = {
                changePassword(!showPassword)
            }) { // Toggle the state when the icon is clicked
                Icon(
                    imageVector = if (showPassword) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                    contentDescription = "toggle_password_visibility"
                )
            }
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    )
}