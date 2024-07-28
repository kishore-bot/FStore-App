package com.example.fstore.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fstore.R
import com.example.fstore.presentation.home.components.HomeSection
import com.example.fstore.presentation.utils.CustomMultiColoredProgressBar
import com.example.fstore.ui.theme.BackgroundColor
import kotlinx.coroutines.time.delay
import java.time.Duration

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier, viewModel: HomeViewModel, random: Int
) {
    val products = viewModel.state.value.products?.collectAsState(initial = null)
    LaunchedEffect(Unit) {
        viewModel.onEvent(HomeEvent.FetchProducts)
    }
    val loading = remember {
        mutableStateOf(true)
    }

    LaunchedEffect(products) {
        if (products != null) {
            delay(duration = Duration.ofMillis(1000))
            loading.value = false
        }
    }
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor),
    ) {
        val bottomPadding = it.calculateBottomPadding()
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(BackgroundColor)
        ) {
            item {
                Box {
                    Image(
                        painter = painterResource(id = homeViewer[random].image),
                        contentDescription = "Home image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(500.dp)
                            .clip(RoundedCornerShape(bottomEnd = 10.dp, bottomStart = 10.dp))
                    )
                    Text(
                        text = homeViewer[random].title,
                        fontSize = 54.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.White,
                        modifier = Modifier
                            .padding(10.dp)
                            .align(
                                Alignment.CenterStart
                            )
                            .offset(y = 80.dp)

                    )
                }
            }
            item {
                if (loading.value) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        CustomMultiColoredProgressBar()
                    }
                } else {
                    if (products != null) {
                        products.value?.let { it1 ->
                            HomeSection(products = it1)
                        }
                    }
                }

            }

            item {
                Spacer(modifier = Modifier.height(bottomPadding + 20.dp))
            }
        }
    }
}


val homeViewer = listOf(
    HomeViewer(
        image = R.drawable.m1, title = "Fashion \n\n\nSale"
    ),
    HomeViewer(
        image = R.drawable.m2, title = "New \n\n\ncollection"
    ),
    HomeViewer(
        image = R.drawable.m3,
        title = "\n" + "\n" + "\n" + "\n" + "Trendy \n" + "\n" + "\n" + "Outfits"
    ),
    HomeViewer(
        image = R.drawable.m5, title = "\nUrban  \n\n\nStyles"
    ),
    HomeViewer(
        image = R.drawable.m4, title = "\nSeasonal\n\n\nFavorites"
    ),
)

data class HomeViewer(
    val image: Int, val title: String
)