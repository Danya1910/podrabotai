package com.example.testapi.presentation.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.testapi.R
import com.example.testapi.presentation.viewModels.AdvertisementViewModel
import com.example.testapi.presentation.widget.Advertisement
import com.example.testapi.presentation.widget.AdvertisementHistory
import com.example.testapi.presentation.widget.CustomTopAppBar
import com.example.testapi.ui.theme.Blue


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun HistoryOfAdvertisementsScreen(
    viewModel: AdvertisementViewModel,
    navController: NavController
) {

    Scaffold(
        topBar = {
            CustomTopAppBar(text = "История просмотра")
        },
        containerColor = Color.Transparent
    ) { innerPadding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = null,
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.Crop
            )

            Content(
                paddingValues = innerPadding,
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}

@Composable
private fun Content(
    viewModel: AdvertisementViewModel,
    navController: NavController,
    paddingValues: PaddingValues
) {

    val message = remember { mutableStateOf("") }
    val activeMessageAdId = remember { mutableStateOf<Int?>(null) }

    val state = viewModel.getHistoryState.value

    LaunchedEffect(Unit) {
        viewModel.loadHistory()
    }

    if (state.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(32.dp)
                    )
            ) {
                CircularProgressIndicator(
                    color = Blue,
                    strokeWidth = 7.dp,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        contentPadding = PaddingValues(
            start = 30.dp,
            end = 30.dp,
        )
    ) {
        when {
            state.isLoading -> {
                item {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                item {
                    Text(
                        text = state.error ?: "Ошибка",
                        color = Color.Red,
                    )
                }
            }

            state.isSuccessful -> {
                items(state.history) { ad ->
                    AdvertisementHistory(
                        ad = ad,
                        navController = navController,
                        viewModel = viewModel,
                        message = message,
                        activeMessageAdId = activeMessageAdId
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}
