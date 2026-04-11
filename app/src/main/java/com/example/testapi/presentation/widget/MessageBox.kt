package com.example.testapi.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.TransparentWhite
import com.example.testapi.ui.theme.White
import kotlinx.coroutines.delay

@Composable
fun MessageBox(
    text: String,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(
                //color = TransparentWhite,
                color = Color.Black.copy(alpha = 0.5f),
                shape = RoundedCornerShape(32.dp)
            )
            .border(
                width = 1.dp,
                color = White,
                shape = RoundedCornerShape(32.dp)
            )
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Text(
            text = text,
            fontFamily = Inter,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            letterSpacing = 1.sp,
            color = Color.White
        )
    }

    LaunchedEffect(Unit) {
        delay(2000)
        onDismiss()
    }
}
