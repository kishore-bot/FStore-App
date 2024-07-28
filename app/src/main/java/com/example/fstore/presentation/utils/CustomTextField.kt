package com.example.fstore.presentation.utils

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.DefaultShadowColor
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    labelText: String = "",
) {
    TextField(
        value = value,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        maxLines = 1,
        modifier = modifier
            .height(64.dp)
            .shadow(
                elevation = 15.dp,
                ambientColor = Color.Gray.copy(alpha = 0.3f),
                spotColor = Color.Gray.copy(alpha = 0.8f)
            )
            .clip(RoundedCornerShape(10.dp)),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            focusedIndicatorColor = Color.White
        ),
        label = { Text(text = labelText, fontWeight = FontWeight.Bold) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    )
}