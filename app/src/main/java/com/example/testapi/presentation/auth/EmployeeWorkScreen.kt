package com.example.testapi.presentation.auth

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testapi.R
import com.example.testapi.domain.model.AdvertisementFilter
import com.example.testapi.domain.model.BottomNavItem
import com.example.testapi.presentation.navigation.Screen
import com.example.testapi.presentation.viewModels.AdvertisementViewModel
import com.example.testapi.presentation.widget.Advertisement
import com.example.testapi.presentation.widget.CustomBottomBar
import com.example.testapi.presentation.widget.MessageBox
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.SupportText
import com.example.testapi.ui.theme.TransparentWhite
import com.example.testapi.ui.theme.White
import com.example.testapi.ui.theme.WhiteClearness80


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun EmployeeWorkScreen(
    viewModel: AdvertisementViewModel,
    navController: NavController,
) {
    val isFiltered = remember { mutableStateOf(false) }
    val filterBtnColor = remember { mutableStateOf(White) }

    val items = listOf(
        BottomNavItem(
            route = Screen.EmployeeWork.route,
            icon = R.drawable.ic_work,
            isFiltered = isFiltered.value
        ),
        BottomNavItem(route = Screen.Favorites.route, icon = R.drawable.ic_navigation_heart),
        BottomNavItem(route = Screen.EmployeeChats.route, icon = R.drawable.ic_message),
        BottomNavItem(route = Screen.EmployeeProfile.route, icon = R.drawable.ic_profile)
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
                    navController = navController,
                    viewModel = viewModel,
                    paddingValues = paddingValues,
                    isFiltered = isFiltered,
                    filterBtnColor = filterBtnColor
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
    filterBtnColor: MutableState<Color>

) {
    val text = remember { mutableStateOf("") }

    val message = remember { mutableStateOf("") }
    val showMessage = remember { mutableStateOf(false) }

    val state = viewModel.getAdvertisementsState.value

    val filter = viewModel.filter.value

    LaunchedEffect(Unit) {
        viewModel.loadAdvertisements(filter = filter)
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
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
            .fillMaxSize()
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
                    text = "Казань",
                    fontFamily = Inter,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    color = White,
                    letterSpacing = 5.sp
                )

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .height(49.dp)
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
                            painter = painterResource(id = R.drawable.ic_person),
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
                text = "Открывай для себя новые вакансии",
                fontFamily = Inter,
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                color = White,
                letterSpacing = 5.sp
            )
        }

        item {
            Spacer(modifier = Modifier.height(10.dp))
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
                items(state.getAdvertisements) { ad ->
                    Advertisement(
                        ad,
                        navController = navController,
                        viewModel = viewModel,
                        message = message,
                        showMessage = showMessage
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
                showError = showMessage,
                modifier = Modifier
                    .fillMaxWidth()
            )
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

fun AdvertisementFilter.isEmpty(): Boolean {
    return car == null &&
            salary == null &&
            age == null &&
            xp == null &&
            address.isNullOrBlank() &&
            date.isNullOrBlank()
}