package com.example.testapi.presentation.screens.auth

import android.util.Log
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testapi.R
import com.example.testapi.presentation.viewModels.LoginViewModel
import com.example.testapi.presentation.navigation.Screen
import com.example.testapi.presentation.widget.LoginInputField
import com.example.testapi.presentation.widget.MessageBox
import com.example.testapi.presentation.widget.CustomTextButton
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Grey
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.White

@Composable
fun RegistrationScreen(
    viewModel: LoginViewModel,
    navController: NavController,
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
                RegistrationContent(
                    viewModel = viewModel,
                    navController = navController,
                    paddingValues = paddingValues,
                )
            }
        )
    }
}

@Composable
fun RegistrationContent(
    viewModel: LoginViewModel,
    navController: NavController,
    paddingValues: PaddingValues,
) {
    val username = remember { mutableStateOf("") }
    val role = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }

    val showError = remember { mutableStateOf(false) }
    val messageError = remember { mutableStateOf("") }

    val usernameFocusRequester = remember { FocusRequester() }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val repeatPasswordFocusRequester = remember { FocusRequester() }

    LaunchedEffect(viewModel.registerState.value.isSuccessful) {
        if (viewModel.registerState.value.isSuccessful) {
            viewModel.localRepository.saveData(
                email = email.value,
                password = password.value
            )
            val data = viewModel.getUserData()
            Log.d("RegistrationScreen", "${data?.email},${data?.password}")
            navController.navigate(Screen.ConfirmMail.route) {
                popUpTo(Screen.Registration.route) {
                    inclusive = true
                }
            }
            viewModel.resetRegister()
        }
    }

    LaunchedEffect(viewModel.registerState.value.error) {
        if (!viewModel.registerState.value.error.isNullOrEmpty()) {
            Log.d("RegistrationScreen", "${viewModel.registerState.value.error}")
        }
    }

    if (viewModel.registerState.value.isLoading) {
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
            text = "Регистрация",
            fontFamily = Inter,
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 5.sp,
            color = White
        )
        Spacer(modifier = Modifier.height(23.dp))
        Text(
            text = "Имя",
            fontFamily = Inter,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 5.sp,
            color = White
        )
        Spacer(modifier = Modifier.height(10.dp))
        LoginInputField(
            text = username,
            focusRequester = usernameFocusRequester,
            nextFocusRequester = null
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Роль",
            fontFamily = Inter,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 5.sp,
            color = White
        )
        Spacer(modifier = Modifier.height(10.dp))
        _choceButtons(text = role)
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Почта",
            fontFamily = Inter,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 5.sp,
            color = White
        )
        Spacer(modifier = Modifier.height(10.dp))
        LoginInputField(
            text = email,
            focusRequester = emailFocusRequester,
            nextFocusRequester = passwordFocusRequester
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Пароль",
            fontFamily = Inter,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 5.sp,
            color = White
        )
        Spacer(modifier = Modifier.height(10.dp))
        LoginInputField(
            text = password,
            focusRequester = passwordFocusRequester,
            nextFocusRequester = repeatPasswordFocusRequester
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
        Spacer(modifier = Modifier.height(10.dp))
        LoginInputField(
            text = confirmPassword,
            focusRequester = repeatPasswordFocusRequester,
            nextFocusRequester = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomTextButton(
            text = "Зарегистрироваться",
            onClick = {
                CheckForm(
                    username = username,
                    email = email,
                    role = role,
                    password = password,
                    repeatPassword = confirmPassword,
                    showError = showError,
                    viewModel = viewModel,
                    messageError = messageError,
                )
            },
            color = Blue
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


private fun CheckForm(
    username: MutableState<String>,
    email: MutableState<String>,
    role: MutableState<String>,
    password: MutableState<String>,
    repeatPassword: MutableState<String>,
    showError: MutableState<Boolean>,
    viewModel: LoginViewModel,
    messageError: MutableState<String>,
) {

    var roleToDto = ""

    val emailPattern = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$".toRegex()

    viewModel.clearErrors()

    if (username.value.isEmpty() || password.value.isEmpty() || role.value.isEmpty()) {
        showError.value = true
        messageError.value = "Заполните все поля"
    } else if (password.value != repeatPassword.value) {
        showError.value = true
        messageError.value = "Пароли не совпадают"
    } else if (viewModel.registerState.value.error == "HTTP 500 INTERNAL SERVER ERROR") {
        showError.value = true
        messageError.value = "Пользователь с такой почтой уже существует"
    } else if (!emailPattern.matches(email.value)) {
        showError.value = true
        messageError.value = "Введите корректную почту"
    } else {
        if (role.value == "Соискатель")
            roleToDto = "finder"
        if (role.value == "Работодатель")
            roleToDto = "employer"
        viewModel.register(
            userName = username.value,
            role = roleToDto,
            email = email.value,
            password = password.value
        )
    }
}


@Composable
fun _choceButtons(
    text: MutableState<String>
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
    ) {
        Button(
            onClick = {
                text.value = "Соискатель"
            },
            modifier = Modifier
                .weight(1f)
                .height(47.dp),
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (text.value == "Соискатель") Blue else Grey
            ),
            border = BorderStroke(width = 1.dp, color = White)
        ) {
            Text(
                text = "Соискатель",
                fontFamily = Inter,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = White
            )
        }
        Spacer(modifier = Modifier.width(5.dp))
        Button(
            onClick = {
                text.value = "Работодатель"
            },
            modifier = Modifier
                .weight(1f)
                .height(47.dp),
            shape = RoundedCornerShape(32.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (text.value == "Работодатель") Blue else Grey
            ),
            border = BorderStroke(width = 1.dp, color = White)
        ) {
            Text(
                text = "Работодатель",
                fontFamily = Inter,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = White
            )
        }
    }
}