package com.example.testapi.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.InspectableModifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.booleanResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapi.ui.theme.BlueForMessage
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.LightBlueForMessage

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MineMessage(
    text: String,
    time: String,
) {


    Row(
        modifier = Modifier.padding(start = 40.dp).fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {

        Box(
            modifier = Modifier
                .background(
                    color = LightBlueForMessage,
                    shape = RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp,
                        bottomStart = 24.dp,
                        bottomEnd = 6.dp
                    )
                )
                .border(
                    1.dp,
                    BlueForMessage,
                    RoundedCornerShape(
                        topStart = 24.dp,
                        topEnd = 24.dp,
                        bottomStart = 24.dp,
                        bottomEnd = 6.dp
                    )
                )
                .padding(start = 12.dp, end = 10.dp, top = 8.dp, bottom = 6.dp)
        ) {

            Text(
                text = text,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Inter,
                color = Color.White,
                lineHeight = 20.sp,
                modifier = Modifier
                    .padding(end = 34.dp, bottom = 2.dp)
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
                    color = Color.White.copy(alpha = 0.8f)
                )
            }
        }
    }
}