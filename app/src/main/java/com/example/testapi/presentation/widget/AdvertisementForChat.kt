package com.example.testapi.presentation.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testapi.R
import com.example.testapi.domain.model.DetailedAdvertisement
import com.example.testapi.presentation.navigation.Screen
import com.example.testapi.presentation.viewModels.AdvertisementViewModel
import com.example.testapi.presentation.viewModels.AdvertisementViewModel_Factory
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Grey
import com.example.testapi.ui.theme.GreyForCorner
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.SupportText
import com.example.testapi.ui.theme.White
import com.example.testapi.ui.theme.Yellow

@Composable
fun AdvertisementForChat(
    ad: DetailedAdvertisement,
    viewModel: AdvertisementViewModel? = null,
    message: MutableState<String>? = null,
    showMessage: MutableState<Boolean>? = null
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(32.dp),
        colors = CardDefaults.cardColors(
            containerColor = White
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 12.dp)
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = ad.title,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter,
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.weight(1f))
                if (viewModel != null) {
                    IconsRow(
                        isUrgent = ad.isUrgent,
                        car = ad.car,
                        viewModel = viewModel,
                        message = message,
                        showMessage = showMessage,
                        jobId = ad.id
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            InfoFields(
                address = ad.address,
                time = calculateWorkHours(timeStart = ad.timeStart, timeEnd = ad.timeEnd)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = ad.salary.toString(),
                fontWeight = FontWeight.SemiBold,
                fontFamily = Inter,
                fontSize = 16.sp,
                color = SupportText
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun IconsRow(
    isUrgent: Boolean,
    car: Boolean,
    message: MutableState<String> ? = null,
    showMessage: MutableState<Boolean>? = null,
    viewModel: AdvertisementViewModel,
    jobId: Int? = null
) {
    val addState = viewModel.addToFavoriteState.value
    val deleteState = viewModel.deleteFromFavoriteState.value
    val isFavorite by viewModel.isFavorite

    val favIcon = if (isFavorite) {
        R.drawable.ic_fill_heart
    } else {
        R.drawable.ic_stroke_heart
    }

    val id = jobId ?: 0

    Row(
        modifier = Modifier
            .height(36.dp)
    ) {
        if (isUrgent)
            CircleIcon(
                route = R.drawable.ic_lightning, color = Yellow, onClick = {
                    message?.value = "Срочное объявление"
                    showMessage?.value = true
                }
            )
        if (car) {
            Spacer(modifier = Modifier.width(8.dp))
            CircleIcon(
                route = R.drawable.ic_car, color = Grey, onClick = {
                    message?.value = "Нужен автомобиль"
                    showMessage?.value = true
                }
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        CircleIcon(
            route = favIcon, color = Color.Unspecified, onClick = {
                if (!addState.isLoading && !deleteState.isLoading) {
                    if (isFavorite) {
                        viewModel.deleteFromFavorite(id)
                    } else {
                        viewModel.addToFavorite(id)
                    }
                }
            }
        )
    }

}

@Composable
private fun InfoFields(
    address: String,
    time: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(30.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(30.dp))
                .border(
                    width = 1.dp,
                    color = Blue,
                    shape = RoundedCornerShape(30.dp)
                )
                .background(Color.White)
                .drawBehind {

                    val shadowColor = Color.Black.copy(alpha = 0.25f)

                    drawRoundRect(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to shadowColor,
                                0.2f to Color.Transparent
                            )
                        ),
                        cornerRadius = CornerRadius(30.dp.toPx())
                    )
                }
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = address,
                fontWeight = FontWeight.Normal,
                fontFamily = Inter,
                fontSize = 15.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
        Spacer(modifier = Modifier.width(15.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .clip(RoundedCornerShape(30.dp))
                .border(
                    width = 1.dp,
                    color = Blue,
                    shape = RoundedCornerShape(30.dp)
                )
                .background(Color.White)
                .drawBehind {

                    val shadowColor = Color.Black.copy(alpha = 0.25f)

                    drawRoundRect(
                        brush = Brush.verticalGradient(
                            colorStops = arrayOf(
                                0.0f to shadowColor,
                                0.2f to Color.Transparent
                            )
                        ),
                        cornerRadius = CornerRadius(30.dp.toPx())
                    )
                }
                .padding(horizontal = 10.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = time,
                fontWeight = FontWeight.Normal,
                fontFamily = Inter,
                fontSize = 15.sp,
                color = Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
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

private fun calculateWorkHours(timeStart: String?, timeEnd: String?): String {
    if (timeStart.isNullOrBlank() || timeEnd.isNullOrBlank()) return ""

    val startParts = timeStart.split(":")
    val endParts = timeEnd.split(":")

    if (startParts.size < 2 || endParts.size < 2) return ""

    val startHour = startParts[0].toIntOrNull() ?: return ""
    val startMinute = startParts[1].toIntOrNull() ?: return ""

    val endHour = endParts[0].toIntOrNull() ?: return ""
    val endMinute = endParts[1].toIntOrNull() ?: return ""

    val startMinutes = startHour * 60 + startMinute
    val endMinutes = endHour * 60 + endMinute

    val diffMinutes = endMinutes - startMinutes
    if (diffMinutes <= 0) return ""

    val hours = diffMinutes / 60.0
    val whole = hours.toInt()
    val decimal = hours - whole

    val rounded = if (decimal < 0.5) whole.toDouble() else whole + 0.5

    // Переводим в Int или 0.5
    val displayHours = if (rounded % 1.0 == 0.0) rounded.toInt() else rounded

    // Функция склонения слова "час"
    fun hoursSuffix(h: Double): String {
        val intPart = h.toInt()
        val mod10 = intPart % 10
        val mod100 = intPart % 100
        return when {
            mod10 == 1 && mod100 != 11 -> "час"
            mod10 in 2..4 && mod100 !in 12..14 -> "часа"
            else -> "часов"
        }
    }

    return "$displayHours ${hoursSuffix(rounded)}"
}