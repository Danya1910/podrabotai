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
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testapi.R
import com.example.testapi.presentation.navigation.Screen
import com.example.testapi.presentation.viewModels.LoginViewModel
import com.example.testapi.presentation.widget.CustomTextButton
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.SupportText
import com.example.testapi.ui.theme.TransparentWhite
import com.example.testapi.ui.theme.White

@Composable
fun ConfirmEmailScreen(
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

    LaunchedEffect(Unit) {
        Log.d("ConfirmationEmailScreen", "${viewModel.temporaryId}")
    }

    LaunchedEffect(viewModel.confirmMailState.value.isSuccessful) {
        if (viewModel.confirmMailState.value.isSuccessful) {
            if(viewModel.confirmMailState.value.confirmMail?.role == "finder") {
                navController.navigate("main_graph") {
                    popUpTo("auth_graph") {
                        inclusive = true
                    }
                }
            }
            else{
                TODO()
            }
        }
    }


    val code = remember { mutableStateOf("") }
    val codeFocusRequester = remember { FocusRequester() }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
            .fillMaxSize()
    ) {
        Text(
            text = "Подтвердите почту",
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
            text = "Мы выслали вам 4х-значный код скопируйте и введите его в поле ниже",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Inter,
            color = White,
            letterSpacing = 3.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 87.dp)
        ) {
            InputCodeField(
                text = code,
                focusRequester = codeFocusRequester,
                nextFocusRequester = null
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextButton(text = "Готово", onClick = {
            viewModel.confirmMail(
                temporaryId = viewModel.temporaryId ?: 0,
                emailCode = code.value.toInt()
            )
        }, color = Blue)
    }
}

@Composable
private fun InputCodeField(
    text: MutableState<String>,
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
                shape = RoundedCornerShape(32.dp)
            )
            .border(
                color = White,
                width = 1.dp,
                shape = RoundedCornerShape(32.dp)
            )
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            BasicTextField(
                value = text.value,
                onValueChange = { newValue ->
                    val filtered = newValue.filter { it.isDigit() }.take(4)
                    text.value = filtered
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                textStyle = TextStyle(
                    color = White,
                    fontSize = 27.sp,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    letterSpacing = 5.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(start = 10.dp)
                    .focusRequester(focusRequester),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        innerTextField()
                    }
                }
            )
            Spacer(modifier = Modifier.width(5.dp))
            Box(
                modifier = Modifier
                    .size(35.dp)
                    .background(Color.White, CircleShape)
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
                    tint = Blue
                )
            }

            Spacer(modifier = Modifier.width(6.dp))
        }
    }
}
