package com.example.fstore.presentation.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.R
import com.example.fstore.domain.model.ProfileModel
import com.example.fstore.utils.cap

@Composable
fun CustomProfileCard(modifier: Modifier = Modifier, profileModel: ProfileModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp), verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.place),
            contentDescription = "Profile image",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Column(modifier = Modifier.padding(start = 10.dp, top = 5.dp)) {
            Text(text = profileModel.name.cap(), fontSize = 26.sp, fontWeight = FontWeight.SemiBold)
            Text(text = profileModel.email, fontSize = 15.sp, fontWeight = FontWeight.Light)
        }
    }
}