package com.example.testapi.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapi.R
import com.example.testapi.presentation.viewModels.AdvertisementViewModel
import com.example.testapi.ui.theme.GreyForCorner
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBarForDetailed(
    text: String,
    viewModel: AdvertisementViewModel,
    jobId: Int
) {
    val addState by viewModel.addToFavoriteState
    val deleteState by viewModel.deleteFromFavoriteState
    val isFavorite by viewModel.isFavorite

    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = text,
                    color = White,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    fontFamily = Inter,
                    letterSpacing = 5.sp
                )

                Spacer(modifier = Modifier.width(8.dp))

                val icon = if (isFavorite) {
                    R.drawable.ic_fill_heart
                } else {
                    R.drawable.ic_stroke_heart
                }

                CircleIcon(
                    route = icon,
                    color = Color.Unspecified,
                    onClick = {
                        if (!addState.isLoading && !deleteState.isLoading) {
                            if (isFavorite) {
                                viewModel.deleteFromFavorite(jobId)
                            } else {
                                viewModel.addToFavorite(jobId)
                            }
                        }
                    }
                )
            }
        },
        navigationIcon = {},
        colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
    )
}

@Composable
private fun CircleIcon(
    route: Int,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(CircleShape)
            .clickable { onClick() }
            .background(Color.White)
            .drawBehind {

                val shadowColor = GreyForCorner

                drawCircle(
                    brush = Brush.radialGradient(
                        colorStops = arrayOf(
                            0.0f to Color.Transparent,
                            0.6f to Color.Transparent,
                            1.0f to shadowColor
                        ),
                        center = center,
                        radius = size.minDimension / 2
                    ),
                    radius = size.minDimension / 2
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(route),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(20.dp)
        )
    }
}