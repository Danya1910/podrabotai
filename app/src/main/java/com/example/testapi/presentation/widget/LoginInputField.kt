package com.example.testapi.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapi.R
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.SupportText
import com.example.testapi.ui.theme.TransparentWhite
import com.example.testapi.ui.theme.White

@Composable
fun LoginInputField(
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
                .fillMaxSize()) {
            BasicTextField(
                value = text.value,
                onValueChange = { text.value = it },
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
                    .clickable{
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