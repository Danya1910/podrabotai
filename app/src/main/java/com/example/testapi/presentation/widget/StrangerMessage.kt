package com.example.testapi.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapi.ui.theme.BlueForMessage
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.LightBlueForMessage
import com.example.testapi.ui.theme.White

@OptIn(ExperimentalLayoutApi::class)
@Composable
@Preview(showBackground = true)
fun StrangerMessage(
    text: String = "Скока сделать надо дебил?",
    time: String = "11:11"
) {

    val screenWidth = LocalConfiguration.current.screenWidthDp.dp

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start
    ) {

        Box(
            modifier = Modifier
                .widthIn(max = screenWidth * 0.7f)
                .background(
                    color = White,
                    shape = RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp,
                        bottomStart = 6.dp,
                        bottomEnd = 24.dp
                    )
                )
                .border(
                    1.dp,
                    LightBlueForMessage,
                    RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp,
                        bottomStart = 6.dp,
                        bottomEnd = 24.dp
                    )
                )
                .padding(start = 12.dp, end = 12.dp, top = 8.dp, bottom = 6.dp)
        ) {

            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Inter,
                color = Color.Black,
                lineHeight = 20.sp,
                modifier = Modifier
                    .padding(end = 26.dp, bottom = 2.dp)
            )

            Row(
                modifier = Modifier
                    .align(Alignment.BottomEnd),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Text(
                    text = time,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter,
                    color = Color.Black.copy(alpha = 0.8f)
                )
            }
        }
    }
}