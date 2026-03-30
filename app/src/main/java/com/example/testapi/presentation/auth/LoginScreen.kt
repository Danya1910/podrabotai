package com.example.testapi.presentation.auth

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testapi.R
import com.example.testapi.domain.repository.LocalDataSourceRepository
import com.example.testapi.presentation.viewModels.LoginViewModel
import com.example.testapi.presentation.navigation.Screen
import com.example.testapi.ui.theme.Inter
import com.example.testapi.presentation.widget.LoginInputField
import com.example.testapi.presentation.widget.CustomTextButton
import com.example.testapi.presentation.widget.MessageBox
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Grey
import com.example.testapi.ui.theme.SupportText
import com.example.testapi.ui.theme.TransparentWhite
import com.example.testapi.ui.theme.White


@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    navController: NavController,
) {
    Box(modifier = Modifier.fillMaxSize()) {
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
    paddingValues: PaddingValues,
) {
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    val showError = remember { mutableStateOf(false) }
    val messageError = remember { mutableStateOf("") }

    val userData = viewModel.getUserData()
    val savedEmail = userData?.email ?: ""

    val email = remember { mutableStateOf(savedEmail) }
    val password = remember { mutableStateOf("") }

    // ИСПРАВЛЕНО: Следим за успешным входом
    LaunchedEffect(viewModel.state.value.isSuccessful) {
        val targetRoute = if (viewModel.state.value.login?.role == "finder") {
            Screen.EmployeeWork.route
        } else {
            Screen.EmployerWork.route
        }
        if (viewModel.state.value.login?.role == "finder" && viewModel.state.value.isSuccessful) {
            viewModel.saveUserData(
                email = email.value,
                password = password.value
            )
            val savedData = viewModel.getUserData()
            Log.d("localRepository", "${savedData?.email}, ${savedData?.password}")
            navController.navigate(targetRoute) {
                popUpTo("auth_graph") {
                    inclusive = true
                }
            }
        } else if ((viewModel.state.value.login?.role == "employer" && viewModel.state.value.isSuccessful)) {
            viewModel.saveUserData(
                email = email.value,
                password = password.value
            )
            val savedData = viewModel.getUserData()
            Log.d("localRepository", "${savedData?.email}, ${savedData?.password}")
            navController.navigate(targetRoute) {
                popUpTo("auth_graph") {
                    inclusive = true
                }
            }
        }
    }

    // ИСПРАВЛЕНО: Следим за ошибками
    LaunchedEffect(viewModel.state.value.error) {
        val error = viewModel.state.value.error
        if (!error.isNullOrEmpty() && error != "HTTP 404 NOT FOUND") {
            showError.value = true
            messageError.value = "Неверные данные для входа"
        }
    }

    if (viewModel.state.value.isLoading) {
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
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {
        Text(
            text = "Авторизация",
            style = TextStyle(
                fontSize = 32.sp,
                color = Color.White,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 5.sp,
                fontFamily = Inter
            )
        )
        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Почта",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Light,
                letterSpacing = 5.sp,
                fontFamily = Inter
            )
        )
        Spacer(modifier = Modifier.height(12.dp))

        LoginPageInputField(
            value = email.value,
            onValueChange = { email.value = it },
            focusRequester = emailFocusRequester,
            nextFocusRequester = passwordFocusRequester
        )
        Spacer(modifier = Modifier.height(9.dp))

        Text(
            text = "Пароль",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.White,
                fontWeight = FontWeight.Light,
                letterSpacing = 5.sp,
                fontFamily = Inter
            )
        )
        Spacer(modifier = Modifier.height(7.dp))

        LoginPageInputField(
            value = password.value,
            onValueChange = { password.value = it },
            focusRequester = passwordFocusRequester,
            nextFocusRequester = null
        )
        Spacer(modifier = Modifier.height(32.dp))

        CustomTextButton(
            text = "Вход",
            onClick = {
                CheckForm(
                    email = email,
                    password = password,
                    showError = showError,
                    messageError = messageError,
                    viewModel = viewModel
                )
            },
            color = Blue
        )
        Spacer(modifier = Modifier.height(15.dp))

        CustomTextButton(
            text = "Регистрация",
            onClick = {
                navController.navigate(Screen.Registration.route)
            },
            color = Blue
        )
        Spacer(modifier = Modifier.height(15.dp))

        CustomTextButton(
            text = "Забыли пароль?",
            onClick = {
                Log.d("Login Screen", "Clicked!!!")
                navController.navigate(Screen.ForgotPassword.route)
            },
            color = Grey
        )
    }

    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp)
    ) {
        if (showError.value) {
            MessageBox(
                text = messageError.value,
                showError = showError,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

private fun CheckForm(
    email: MutableState<String>,
    password: MutableState<String>,
    showError: MutableState<Boolean>,
    viewModel: LoginViewModel,
    messageError: MutableState<String>,
) {
    val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    viewModel.clearErrors()

    when {
        password.value.isEmpty() || email.value.isEmpty() -> {
            showError.value = true
            messageError.value = "Заполните все поля"
        }

        !emailPattern.matches(email.value) -> {
            showError.value = true
            messageError.value = "Введите корректную почту"
        }

        else -> {
            viewModel.login(
                email = email.value,
                password = password.value
            )
        }
    }
}

@Composable
private fun LoginPageInputField(
    value: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester,
    nextFocusRequester: FocusRequester?
) {

    val focusManager = LocalFocusManager.current

    Box(
        modifier = Modifier
            .height(47.dp)
            .fillMaxWidth()
            .background(
                color = TransparentWhite,
                shape = RoundedCornerShape(size = 32.dp)
            )
            .border(
                color = White,
                width = 1.dp,
                shape = RoundedCornerShape(size = 32.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
        ) {
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                singleLine = true,
                textStyle = TextStyle(color = SupportText, fontSize = 16.sp),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .height(47.dp)
                    .padding(start = 16.dp, end = 16.dp)
                    .focusRequester(focusRequester),
                decorationBox = { innerTextField ->
                    Box(
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .background(color = Color.White, shape = CircleShape)
                    .clickable {
                        if (nextFocusRequester != null) {
                            nextFocusRequester.requestFocus()
                        } else {
                            focusManager.clearFocus()
                        }
                    },
                contentAlignment = Alignment.Center

            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_tick),
                    contentDescription = null,
                    tint = Blue,
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
        }
    }
}