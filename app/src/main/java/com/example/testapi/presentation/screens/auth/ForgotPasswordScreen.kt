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
import androidx.compose.runtime.collectAsState
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
import com.example.testapi.presentation.navigation.Screen
import com.example.testapi.presentation.widget.CustomTextButton
import com.example.testapi.presentation.widget.LoginInputField
import com.example.testapi.presentation.widget.MessageBox
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.White

@Composable
fun ForgotPasswordScreen(
    viewModel: LoginViewModel,
    navController: NavController
) {
    BackHandler {
        navController.popBackStack()
    }
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
    val email = remember { mutableStateOf("") }
    val emailFocusRequester = remember { FocusRequester() }

    val showError = remember { mutableStateOf(false) }
    val messageError = remember { mutableStateOf("") }

    LaunchedEffect(viewModel.forgotPasswordState.collectAsState().value.isSuccessful) {
        if (viewModel.forgotPasswordState.value.isSuccessful) {
            navController.navigate(Screen.RecoveryCode.route)
        }
    }

    LaunchedEffect(viewModel.forgotPasswordState.collectAsState().value.error) {
        if (!viewModel.forgotPasswordState.value.error.isNullOrEmpty()) {
            showError.value = true
            messageError.value = "Ошибка"
        }
    }

    if (viewModel.forgotPasswordState.collectAsState().value.isLoading) {
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
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
            .fillMaxSize()
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
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Для восстановления пароля введите почту",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Inter,
            color = White,
            letterSpacing = 3.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(24.dp))
        LoginInputField(
            text = email,
            focusRequester = emailFocusRequester,
            nextFocusRequester = null
        )
        Spacer(modifier = Modifier.height(24.dp))
        CustomTextButton(
            text = "Готово",
            onClick = {
                forgotPassword(
                    viewModel = viewModel,
                    email = email,
                    showError = showError,
                    messageError = messageError
                )
            },
            color = Blue
        )
    }
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (showError.value) {
            MessageBox(
                text = messageError.value,
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

private fun forgotPassword(
    viewModel: LoginViewModel,
    email: MutableState<String>,
    showError: MutableState<Boolean>,
    messageError: MutableState<String>
) {
    if (email.value.isEmpty()) {
        showError.value = true
        messageError.value = "Введите почту"
    } else if (!email.value.contains("@")) {
        showError.value = true
        messageError.value = "Введите корректную почту"
    } else {
        viewModel.forgotPassword(email = email.value)
    }
}
