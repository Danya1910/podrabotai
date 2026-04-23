package com.example.testapi.presentation.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testapi.R
import com.example.testapi.domain.model.BottomNavItem
import com.example.testapi.presentation.navigation.Screen
import com.example.testapi.presentation.viewModels.AdvertisementViewModel
import com.example.testapi.presentation.widget.CustomBottomBar
import com.example.testapi.presentation.widget.CustomTextButton
import com.example.testapi.presentation.widget.CustomTopAppBar
import com.example.testapi.presentation.widget.MessageBox
import com.example.testapi.presentation.widget.OwnAdvertisement
import com.example.testapi.ui.theme.Blue


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun MyAdvertisementsScreen(
    viewModel: AdvertisementViewModel,
    navController: NavController
) {
    val items = listOf(
        BottomNavItem(route = Screen.EmployerWork.route, icon = R.drawable.ic_work),
        BottomNavItem(route = Screen.MyAdvertisements.route, icon = R.drawable.ic_megaphone),
        BottomNavItem(route = Screen.EmployerChats.route, icon = R.drawable.ic_message),
        BottomNavItem(route = Screen.EmployerProfile.route, icon = R.drawable.ic_profile)
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )

        Scaffold(
            topBar = {
                CustomTopAppBar(
                    text = "Мои объявления",
                )
            },
            containerColor = Color.Transparent,
            bottomBar = {
                CustomBottomBar(
                    items = items,
                    currentRoute = currentRoute,
                    onItemClick = { item ->
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                )
            },
            content = { paddingValues ->
                Content(
                    paddingValues = paddingValues,
                    navController = navController,
                    viewModel = viewModel
                )
            }
        )
    }
}

@Composable
private fun Content(
    viewModel: AdvertisementViewModel,
    navController: NavController,
    paddingValues: PaddingValues
) {

    val message = remember { mutableStateOf("") }
    val showMessage = remember { mutableStateOf(false) }


    val state = viewModel.getMyAdvertisementsState.value

    LaunchedEffect(Unit) {
        viewModel.loadMyAdvertisements()
    }

    if(state.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
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
            .fillMaxSize(),
        contentPadding = PaddingValues(
            top = paddingValues.calculateTopPadding(),
            start = 30.dp,
            end = 30.dp,
            bottom = 80.dp
        )
    ) {

        item {
            CustomTextButton(
                text = "+ Новое объявление",
                color = Blue,
                onClick = {
                    navController.navigate(Screen.CreateAdvertisement.route)
                }
            )
        }

        item {
            Spacer(modifier = Modifier.height(15.dp))
        }

        when {


            state.error != null -> {
                item {
                    Text(
                        text = state.error ?: "Ошибка",
                        color = Color.Red,
                    )
                }
            }

            state.isSuccessful -> {
                items(state.myAds) { ad ->
                    OwnAdvertisement(
                        ad,
                        navController = navController,
                        viewModel = viewModel,
                        message = message,
                        showMessage = showMessage,
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 90.dp)
    ) {
        if (showMessage.value) {
            MessageBox(
                text = message.value,
                onDismiss = {
                    showMessage.value = false
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

