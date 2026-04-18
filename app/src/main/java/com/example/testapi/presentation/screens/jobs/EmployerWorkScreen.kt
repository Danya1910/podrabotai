package com.example.testapi.presentation.auth

import android.Manifest
import android.os.Build
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testapi.R
import com.example.testapi.domain.model.BottomNavItem
import com.example.testapi.presentation.navigation.Screen
import com.example.testapi.presentation.screens.jobs.isEmpty
import com.example.testapi.presentation.viewModels.AdvertisementViewModel
import com.example.testapi.presentation.viewModels.BigDataCloudViewModel
import com.example.testapi.presentation.viewModels.LocationViewModel
import com.example.testapi.presentation.widget.AdvertisementForEmployer
import com.example.testapi.presentation.widget.CustomBottomBar
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.SupportText
import com.example.testapi.ui.theme.TransparentWhite
import com.example.testapi.ui.theme.White
import com.example.testapi.ui.theme.WhiteClearness80
import kotlinx.coroutines.delay


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun EmployerWorkScreen(
    viewModel: AdvertisementViewModel,
    navController: NavController,
    locationViewModel: LocationViewModel,
    bigDataCloudViewModel: BigDataCloudViewModel,
) {
    BackHandler(enabled = true) {}
    val isFiltered = remember { mutableStateOf(false) }
    val filterBtnColor = remember { mutableStateOf(White) }

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
            containerColor = Color.Transparent,
            bottomBar = {
                CustomBottomBar(
                    items = items,
                    currentRoute = currentRoute,
                    onItemClick = { item ->
                        val isSameScreen = currentRoute == item.route
                        if (isSameScreen && item.isFiltered == true) {
                            viewModel.loadAdvertisements(filter = null)
                            filterBtnColor.value = White
                        } else {
                            navController.navigate(item.route) {
                                launchSingleTop = true
                                restoreState = true
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                            }
                        }
                    }
                )
            },
            content = { paddingValues ->
                Content(
                    paddingValues = paddingValues,
                    navController = navController,
                    viewModel = viewModel,
                    isFiltered = isFiltered,
                    filterBtnColor = filterBtnColor,
                    locationViewModel = locationViewModel,
                    bigDataCloudViewModel = bigDataCloudViewModel,
                )
            }
        )
    }
}

@Composable
private fun Content(
    viewModel: AdvertisementViewModel,
    navController: NavController,
    paddingValues: PaddingValues,
    isFiltered: MutableState<Boolean>,
    filterBtnColor: MutableState<Color>,
    locationViewModel: LocationViewModel,
    bigDataCloudViewModel: BigDataCloudViewModel,
) {

    val text = remember { mutableStateOf("") }

    val message = remember { mutableStateOf("") }
    val activeMessageAdId = remember { mutableStateOf<Int?>(null) }

    val state = viewModel.getAdvertisementsState.value

    val filter = viewModel.filter.value

    val location = locationViewModel.locationState.value

    val position = bigDataCloudViewModel.positionState.value
    val city = remember { mutableStateOf("") }
    val dots = remember { mutableStateOf("") }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { granted ->
        if (granted) {
            locationViewModel.loadLocation()
        }
    }

    LaunchedEffect(Unit) {
        launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        viewModel.loadAdvertisements(filter = filter)
        while (true) {
            dots.value = when (dots.value) {
                "" -> "."
                "." -> ".."
                ".." -> "..."
                else -> ""
            }
            delay(500)
        }
    }

    LaunchedEffect(filter) {
        if (!filter.isEmpty()) {
            isFiltered.value = true
            Log.d("EmployeeWorkScreen", "isFilter : $isFiltered")
            filterBtnColor.value = Blue
        } else {
            isFiltered.value = false
            Log.d("EmployeeWorkScreen", "isFilter : $isFiltered")
            filterBtnColor.value = White

        }
    }

    LaunchedEffect(location) {
        if (location.location != null && location.error != "Не удалось получить координаты") {
            bigDataCloudViewModel.loadPosition(
                lat = location.location.latitude,
                lng = location.location.longitude
            )
        }
    }

    if (!position.position?.city.isNullOrBlank()) {
        city.value = position.position.city
    } else if (!position.error.isNullOrBlank()) {
        city.value = "Неизвестно"
    } else {
        city.value = "Загрузка${dots.value}"
    }

    if (state.isLoading) {
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_location),
                    contentDescription = null,
                    tint = Color.Unspecified
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = city.value,
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    color = White,
                    letterSpacing = 3.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f) // 👈 ВОТ ЭТО ГЛАВНОЕ
                )


                Box(
                    modifier = Modifier
                        .height(49.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .border(
                            width = 1.dp,
                            color = White,
                            shape = RoundedCornerShape(32.dp)
                        )
                        .background(
                            color = TransparentWhite,
                            shape = RoundedCornerShape(32.dp)
                        )
                        .clickable {
                            navController.navigate(Screen.EmployeeProfile.route)
                        },

                    ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 10.dp, end = 2.dp)
                    ) {
                        Text(
                            text = "Профиль",
                            fontFamily = Inter,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = White,
                            letterSpacing = 5.sp
                        )
                        Spacer(modifier = Modifier.width(7.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.ic_avatar),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(15.dp))
        }

        item {
            Text(
                text = "Здесь будет ваше объявление",
                fontFamily = Inter,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                color = White,
                letterSpacing = 5.sp
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }

        item {
            SearchingField(
                text = text,
                navController = navController,
                filterBtnColor = filterBtnColor
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
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
                items(state.ads) { ad ->
                    AdvertisementForEmployer(
                        ad,
                        message = message,
                        activeMessageAdId = activeMessageAdId
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

@Composable
private fun SearchingField(
    text: MutableState<String>,
    navController: NavController,
    filterBtnColor: MutableState<Color>
) {
    Box(
        modifier = Modifier
            .height(53.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = White,
                shape = RoundedCornerShape(32.dp)
            )
            .background(
                color = WhiteClearness80,
                shape = RoundedCornerShape(32.dp)
            ),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(all = 4.dp)
                .fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .height(53.dp)
                    .border(
                        width = 1.dp,
                        color = White,
                        shape = RoundedCornerShape(32.dp)
                    )
                    .background(
                        color = TransparentWhite,
                        shape = RoundedCornerShape(32.dp)
                    ),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(start = 13.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    BasicTextField(
                        value = text.value,
                        onValueChange = { text.value = it },
                        singleLine = true,
                        textStyle = TextStyle(color = SupportText, fontSize = 16.sp),
                        modifier = Modifier
                            .height(47.dp),
                        decorationBox = { innerTextField ->
                            Box(
                                contentAlignment = Alignment.CenterStart,
                            ) {
                                if (text.value.isEmpty()) {
                                    Text(
                                        text = "Поиск",
                                        color = SupportText.copy(alpha = 0.4f),
                                        fontSize = 16.sp
                                    )
                                }
                                innerTextField()
                            }
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.weight(1f))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(49.dp)
                    .background(color = filterBtnColor.value, shape = CircleShape)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        navController.navigate(Screen.Filter.route)
                    }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_settings),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}