package com.example.testapi.presentation.screens.auth

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testapi.R
import com.example.testapi.presentation.viewModels.LoginViewModel
import com.example.testapi.presentation.widget.CustomTextButton
import com.example.testapi.presentation.widget.LoginInputField
import com.example.testapi.presentation.widget.MessageBox
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.White

@Composable
fun RecoveryPasswordScreen(
    viewModel: LoginViewModel,
    navController: NavController
) {
    Box(modifier = Modifier.fillMaxSize()) { // фон на весь экран
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        Scaffold(
            containerColor = Color.Transparent,
            content = { paddingValues ->
                Content(
                    viewModel = viewModel,
                    navController = navController,
                    paddingValues = paddingValues
                )

            }
        )
    }
}

@Composable
private fun Content(
    viewModel: LoginViewModel,
    navController: NavController,
    paddingValues: PaddingValues
) {
    BackHandler {
        navController.popBackStack()
    }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }
    val showError = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }

    LaunchedEffect(viewModel.recoveryPasswordState.value.isSuccessful) {
        if (viewModel.recoveryPasswordState.value.isSuccessful) {
            navController.navigate("main_graph") {
                popUpTo("auth_graph") {
                    inclusive = true
                }
            }
        }
    }


    if (viewModel.recoveryPasswordState.value.isLoading) {
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


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(paddingValues)
    ) {
        Text(
            text = "Забыли пароль?",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = Inter,
            color = White,
            letterSpacing = 5.sp,
            lineHeight = 30.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Новый пароль",
            fontFamily = Inter,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 5.sp,
            color = White
        )
        Spacer(modifier = Modifier.height(7.dp))
        LoginInputField(
            text = password,
            focusRequester = passwordFocusRequester,
            nextFocusRequester = confirmPasswordFocusRequester
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Подтвердите пароль",
            fontFamily = Inter,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 5.sp,
            color = White
        )
        Spacer(modifier = Modifier.height(7.dp))
        LoginInputField(
            text = confirmPassword,
            focusRequester = passwordFocusRequester,
            nextFocusRequester = null
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextButton(text = "Готово", onClick = {
            recoveryPassword(
                viewModel = viewModel,
                password = password,
                confirmPassword = confirmPassword,
                showError = showError,
                errorMessage = errorMessage
            )
        }, color = Blue)
    }
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (showError.value) {
            MessageBox(
                text = errorMessage.value,
                onDismiss = {
                    showError.value = false
                },
                modifier = Modifier
                    .padding(bottom = 30.dp)
                    .fillMaxWidth()
            )
        }
    }
}


private fun recoveryPassword(
    viewModel: LoginViewModel,
    password: MutableState<String>,
    confirmPassword: MutableState<String>,
    showError: MutableState<Boolean>,
    errorMessage: MutableState<String>
) {
    if (password.value != confirmPassword.value) {
        showError.value = true
        errorMessage.value = "Пароли должны совпадать"
    } else if (password.value.isEmpty()) {
        showError.value = true
        errorMessage.value = "Введите пароль"
    } else {
        viewModel.recoveryPassword(
            temporaryId = viewModel.temporaryId ?: 0,
            code = viewModel.code ?: 0,
            password = password.value
        )
    }
}