package com.example.fstore.presentation.reviews.create_review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.domain.model.send.CreateReviewModel
import com.example.fstore.presentation.reviews.ReviewEvent
import com.example.fstore.presentation.reviews.ReviewViewModel
import com.example.fstore.presentation.utils.CustomButton
import com.example.fstore.presentation.utils.CustomTextField
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.ui.theme.StarColor
import com.example.fstore.ui.theme.TextLighterShade

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateReview(
    modifier: Modifier = Modifier,
    sheetState: SheetState,
    bottomSheetState: MutableState<Boolean>,
    onClick: () -> Unit,
    viewModel: ReviewViewModel
) {
    val commentText = remember { mutableStateOf(TextFieldValue("")) }
    val rating = remember { mutableIntStateOf(0) }


    FloatingActionButton(
        modifier = modifier,
        onClick = { onClick.invoke() },
        shape = RoundedCornerShape(30.dp),
        containerColor = ButtonColor,
        elevation = FloatingActionButtonDefaults.elevation(2.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 10.dp, end = 13.dp, top = 5.dp, bottom = 5.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = "Create Review",
                modifier = Modifier.size(20.dp),
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "Write a review", color = Color.White, fontSize = 16.sp)
        }

    }
    if (bottomSheetState.value) {
        ModalBottomSheet(
            onDismissRequest = { bottomSheetState.value = false },
            modifier = modifier,
            sheetState = sheetState
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "What is you rate?", fontSize = 24.sp, fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            CustomStar(
                onClick = { i ->
                    rating.intValue = i;
                },
                j = rating.intValue,
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Please share your opinion\n" + "\t\t\tabout the product",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            CustomTextField(
                value = commentText.value,
                onValueChange = { newTextFieldValue ->
                    commentText.value = newTextFieldValue
                },
                labelText = "Your Review",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .padding(20.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                CustomButton(onCustomButtonClick = {
                    postReview(
                        comment = commentText.value.text,
                        rating = rating.intValue,
                        viewModel = viewModel
                    )
                    commentText.value = TextFieldValue("")
                    rating.intValue = 0
                    bottomSheetState.value = false
                }, buttonText = "SEND REVIEW")
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}

@Composable
fun CustomStar(modifier: Modifier = Modifier, onClick: (Int) -> Unit, j: Int) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        for (i in 1..5) {
            IconButton(onClick = { onClick(i) }) {
                if (i <= j) {
                    Icon(
                        imageVector = Icons.Filled.Star,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = StarColor
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.Star,
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = TextLighterShade
                    )
                }
            }
        }
    }
}

@Composable
fun CustomPicture() {
}

private fun postReview(
    comment: String? = null, rating: Int, viewModel: ReviewViewModel
) {
    val id = viewModel.state.value.id
    val review = id?.let {
        CreateReviewModel(
            productId = it, comment = comment, rating = rating
        )
    }
    review?.let { ReviewEvent.UploadReview(review = it) }?.let { viewModel.onEvent(it) }
    viewModel.onEvent(ReviewEvent.ReviewProduct)
}