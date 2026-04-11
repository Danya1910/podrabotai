package com.example.testapi.presentation.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testapi.R
import com.example.testapi.domain.model.BottomNavItem
import com.example.testapi.presentation.navigation.Screen
import com.example.testapi.presentation.viewModels.LoginViewModel
import com.example.testapi.presentation.viewModels.UserViewModel
import com.example.testapi.presentation.widget.CustomBottomBar
import com.example.testapi.presentation.widget.CustomTextButton
import com.example.testapi.presentation.widget.CustomTopAppBar
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.White


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun EmployerProfileScreen(
    viewModel: UserViewModel,
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

    LaunchedEffect(Unit) {
        viewModel.loadProfile()
    }

    val authViewModel: LoginViewModel? = navBackStackEntry?.let { hiltViewModel(it) }

    if (authViewModel == null) return


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

        Scaffold(
            containerColor = Color.Transparent,
            topBar = {
                CustomTopAppBar(
                    text = "Профиль",
                    onLogoutClick = {
                        authViewModel.logout()
                        navController.navigate(Screen.Login.route) {
                            popUpTo(Screen.Login.route) { inclusive = true }
                            launchSingleTop = true
                        }
                    }
                )
            },
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
                    viewModel = viewModel,
                    paddingValues = paddingValues,
                    navController = navController,
                    authViewModel = authViewModel
                )
            }
        )
    }
}

@Composable
private fun Content(
    viewModel: UserViewModel,
    paddingValues: PaddingValues,
    navController: NavController,
    authViewModel: LoginViewModel
) {

    val profile = viewModel.getProfileState.value.profile



    LaunchedEffect(authViewModel.changeRoleState.value.isSuccessful) {
        if (!authViewModel.changeRoleState.value.isSuccessful) return@LaunchedEffect
        if (authViewModel.changeRoleState.value.changeRole == "Роль успешно изменена на finder") {
            navController.navigate(Screen.EmployeeWork.route) {
                launchSingleTop = true
                restoreState = true
            }
        } else {
            navController.navigate(Screen.EmployerWork.route) {
                launchSingleTop = true
                restoreState = true
            }
        }
        authViewModel.resetChangeRoleState()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .height(63.dp)
                .shadow(elevation = 8.dp, shape = RoundedCornerShape(32.dp), clip = false)
                .background(color = White, shape = RoundedCornerShape(32.dp))
                .padding(horizontal = 13.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_avatar),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = profile?.userName ?: "Неизвестно",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
        CustomTextButton(
            text = "Сменить роль",
            color = Blue,
            onClick = {
                authViewModel.changeRole()
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        CustomTextButton(
            text = "Сменить пароль",
            color = Blue,
            onClick = {
                navController.navigate(Screen.ChangePassword.route)
            }
        )
    }
}
