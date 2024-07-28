package com.example.fstore.presentation.details.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.ui.theme.ButtonColor
import com.example.fstore.ui.theme.DetailsScreenColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomSizeScreen(
    modifier: Modifier = Modifier,
    onBoxClick: () -> Unit,
    sheetState: SheetState,
    bottomSheetState: MutableState<Boolean>,
    size: List<String>,
    selectedSizeIndex: MutableState<Int>
) {
    Box(modifier = modifier
        .height(40.dp)
        .background(DetailsScreenColor)
        .clickable { onBoxClick() }
        .border(
            width = 0.5.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)
        )
        .clip(RoundedCornerShape(10.dp))
        .padding(8.dp)) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (selectedSizeIndex.value == -1) "Size" else size[selectedSizeIndex.value].uppercase(),
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.width(50.dp))
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Arrow Down",
                modifier = Modifier.size(25.dp)
                    .align(Alignment.Bottom)
            )
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
                    text = "Selected Size", fontSize = 20.sp, fontWeight = FontWeight.Bold
                )
            }
            CustomSizeChart(size = size, onSizeSelect = { it ->
                selectedSizeIndex.value = it
                bottomSheetState.value = false
            }, selectedSizeIndex = selectedSizeIndex.value)
            CustomDetailsSupportCard(text = "Size Info", onclick = {})
            Spacer(modifier = Modifier.height(30.dp))
        }
    }
}


@Composable
fun CustomSizeChart(
    modifier: Modifier = Modifier,
    size: List<String>,
    onSizeSelect: (Int) -> Unit,
    selectedSizeIndex: Int
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        columns = GridCells.Fixed(3)
    ) {
        items(size.size) { index ->
            Box(modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(if (selectedSizeIndex == index) ButtonColor else Color.White)
                .clickable { onSizeSelect(index) }
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center) {
                Text(text = size[index].uppercase(), fontSize = 16.sp, modifier = Modifier.padding(10.dp))
            }
        }
    }
}