package com.example.testapi.presentation.screens.auth

import android.util.Log
import androidx.compose.foundation.Image
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
import com.example.testapi.presentation.navigation.Screen
import com.example.testapi.presentation.viewModels.LoginViewModel
import com.example.testapi.presentation.widget.CustomTextButton
import com.example.testapi.presentation.widget.LoginInputField
import com.example.testapi.presentation.widget.MessageBox
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.White

@Composable
fun ChangePasswordScreen(
    viewModel: LoginViewModel,
    navController: NavController
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
    paddingValues: PaddingValues
) {
    val oldPassword = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val confirmPassword = remember { mutableStateOf("") }
    val oldPasswordFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }
    val confirmPasswordFocusRequester = remember { FocusRequester() }
    val showError = remember { mutableStateOf(false) }
    val errorMessage = remember { mutableStateOf("") }

    LaunchedEffect(viewModel.changePasswordState.collectAsState().value.isSuccessful) {
        Log.d(
            "ChangePasswordScreen",
            "Role: ${viewModel.changePasswordState.value.changePassword?.role}"
        )
        if (viewModel.changePasswordState.value.changePassword?.role == "finder") {
            navController.navigate(Screen.EmployeeProfile.route) {
                popUpTo(Screen.EmployeeProfile.route) {
                    inclusive = true
                }
            }
        } else if (viewModel.changePasswordState.value.changePassword?.role == "employee") {
            //Навигация на Employer
            navController.navigate(Screen.EmployeeProfile.route) {
                popUpTo(Screen.EmployeeProfile.route) {
                    inclusive = true
                }
            }
        }
    }

    LaunchedEffect(viewModel.changePasswordState.collectAsState().value.error) {
        Log.d("ChangePasswordScreen", "${viewModel.changePasswordState.value.error}")
        val error = viewModel.changePasswordState.value.error
        if (!error.isNullOrEmpty()) {
            showError.value = true
            errorMessage.value = viewModel.changePasswordState.value.error.toString()
        }
    }


    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 25.dp)
    ) {
        Text(
            text = "Сменить пароль",
            fontSize = 32.sp,
            fontWeight = FontWeight.ExtraBold,
            fontFamily = Inter,
            color = White,
            letterSpacing = 5.sp,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = Modifier.height(36.dp))
        Text(
            text = "Введите старый пароль",
            fontFamily = Inter,
            fontSize = 14.sp,
            fontWeight = FontWeight.Light,
            letterSpacing = 5.sp,
            color = White
        )
        Spacer(modifier = Modifier.height(7.dp))
        LoginInputField(
            text = oldPassword,
            focusRequester = oldPasswordFocusRequester,
            nextFocusRequester = passwordFocusRequester
        )
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "Введите новый пароль",
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
            focusRequester = confirmPasswordFocusRequester,
            nextFocusRequester = null
        )
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextButton(text = "Готово", onClick = {
            changePassword(
                viewModel = viewModel,
                newPassword = password.value,
                oldPassword = oldPassword.value,
                confirmPassword = confirmPassword.value,
                showError = showError,
                errorMessage = errorMessage,
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

private fun changePassword(
    viewModel: LoginViewModel,
    oldPassword: String,
    newPassword: String,
    confirmPassword: String,
    showError: MutableState<Boolean>,
    errorMessage: MutableState<String>
) {
    if (newPassword != confirmPassword) {
        showError.value = true
        errorMessage.value = "Пароли должны совпадать"
    } else if (newPassword.isEmpty() || confirmPassword.isEmpty() || oldPassword.isEmpty()) {
        showError.value = true
        errorMessage.value = "Заполните все поля"
    } else if (newPassword.length < 8) {
        showError.value = true
        errorMessage.value = "Паполь должен быть больше 8 символов"
    } else {
        viewModel.changePassword(old_password = oldPassword, new_password = newPassword)
        viewModel.clearErrors()
    }

}