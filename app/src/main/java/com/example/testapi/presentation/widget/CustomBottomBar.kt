package com.example.testapi.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import android.os.Build
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import android.graphics.RenderEffect
import android.graphics.Shader
import androidx.annotation.RequiresApi
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.testapi.domain.model.BottomNavItem
import com.example.testapi.ui.theme.BackgroundForInputField
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.GreyForCorner
import com.example.testapi.ui.theme.TransparentWhite
import com.example.testapi.ui.theme.White
import com.example.testapi.ui.theme.WhiteClearness80

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun CustomBottomBar(
    items: List<BottomNavItem>,
    currentRoute: String?,
    onItemClick: (BottomNavItem) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .padding(horizontal = 25.dp)
            .height(64.dp)
            .background(
                Color.White.copy(alpha = 0.8f),
                shape = RoundedCornerShape(35.dp)
            )
            .border(
                width = 1.dp,
                color = White,
                shape = RoundedCornerShape(35.dp)
            )
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .graphicsLayer {
                    renderEffect = RenderEffect.createBlurEffect(
                        40f, 40f, Shader.TileMode.CLAMP
                    ).asComposeRenderEffect()
                    clip = true
                    shape = RoundedCornerShape(35.dp)
                }
                .background(BackgroundForInputField.copy(alpha = 0.5f))
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .padding(horizontal = 3.dp)
                .fillMaxSize()
        ) {
            items.forEach { item ->
                val selected = currentRoute == item.route
                val tintColor = if (!selected) Color.Transparent else Blue
                Box(
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape)
                        .border(width = 1.dp, color = White, shape = CircleShape)
                        .background(color = tintColor, shape = CircleShape)
                        .clickable { onItemClick(item) },
                    contentAlignment = Alignment.Center
                )
                {
                    if (!selected) {
                        Box(
                            modifier = Modifier
                                .matchParentSize()
                                .clip(RoundedCornerShape(32.dp))
                                .background(
                                    White.copy(alpha = 0.5f),
                                    RoundedCornerShape(32.dp)
                                )
                        )
                    }
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null,
                        tint = Color.Black,
                    )
                }
            }
        }
    }
}