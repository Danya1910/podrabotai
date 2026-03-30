package com.example.testapi.presentation.auth

import android.R.attr.padding
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testapi.R
import com.example.testapi.domain.model.DetailedAdvertisement
import com.example.testapi.presentation.viewModels.AdvertisementViewModel
import com.example.testapi.presentation.widget.CustomTopAppBar
import com.example.testapi.presentation.widget.CustomTopAppBarForDetailed
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.SupportText
import com.example.testapi.ui.theme.White
import java.time.LocalTime
import kotlin.time.Duration

@Composable
fun DetailedAdvertisementScreen(
    viewModel: AdvertisementViewModel,
    navController: NavController,
    jobId: Int
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
            topBar = {
                CustomTopAppBarForDetailed(
                    text = "Подробнее",
                    viewModel = viewModel,
                    jobId = jobId
                )
            },
            bottomBar = {
                // твой CustomBottomBar
            },
            content = { paddingValues ->
                Content(
                    viewModel = viewModel,
                    navController = navController,
                    jobId = jobId,
                    paddingValues = paddingValues
                )
            }
        )
    }
}

@Composable
private fun Content(
    viewModel: AdvertisementViewModel,
    navController: NavController,
    jobId: Int,
    paddingValues: PaddingValues,
) {

    val ad = viewModel.getDetailedAdvertisementState.value.getDetailedAdvertisement

    LaunchedEffect(jobId) {
        viewModel.loadDetailedAdvertisement(jobId = jobId)
        viewModel.addToHistory(jobId = jobId)
    }


    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
            .verticalScroll(rememberScrollState())
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = White,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(all = 15.dp)
        ) {
            Column(
            ) {
                Text(
                    text = ad?.title ?: "",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Адрес подработки",
                    color = SupportText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = ad?.address ?: "",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Дата подработки",
                    color = SupportText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = ad?.date ?: "",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Время подработки",
                    color = SupportText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = calculateWorkHours(
                        timeStart = ad?.timeStart ?: "",
                        timeEnd = ad?.timeEnd ?: ""
                    ),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Зарплата",
                    color = SupportText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = ad?.salary.toString(),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = White,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(vertical = 10.dp, horizontal = 16.dp)
        ) {
            Column() {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_person),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = ad?.user?.userName ?: "Неизвестно",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = Inter
                    )
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = null,
                        tint = Blue
                    )
                }
                Spacer(modifier = Modifier.height(17.dp))
                CommunicateButtons(onCallClick = {}, onWriteClick = {})
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = White,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(all = 15.dp)
        ) {
            Column() {
                Text(
                    text = "Опыт работы",
                    color = SupportText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = ad?.xp.toString(),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Возраст",
                    color = SupportText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = ad?.age.toString(),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Описание",
                    color = SupportText,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = ad?.description ?: "",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = Inter
                )

            }
        }
    }
}

@Composable
private fun CommunicateButtons(
    onCallClick: () -> Unit,
    onWriteClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
                .clickable { onCallClick() }
                .border(
                    width = 1.dp,
                    color = Blue,
                    shape = RoundedCornerShape(22.dp)
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(22.dp)
                )
                .padding(horizontal = 22.dp, vertical = 11.dp)
        ) {
            Text(
                text = "Позвонить",
                color = Blue,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Inter
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .height(40.dp)
                .weight(1f)
                .clickable { onWriteClick() }
                .border(
                    width = 1.dp,
                    color = Blue,
                    shape = RoundedCornerShape(22.dp)
                )
                .background(
                    color = Blue,
                    shape = RoundedCornerShape(22.dp)
                )
                .padding(horizontal = 22.dp, vertical = 11.dp)
        ) {
            Text(
                text = "Написать",
                color = White,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = Inter
            )
        }
    }
}

private fun calculateWorkHours(timeStart: String?, timeEnd: String?): String {

    if (timeStart.isNullOrBlank() || timeEnd.isNullOrBlank()) {
        return ""
    }

    val startParts = timeStart.split(":")
    val endParts = timeEnd.split(":")

    if (startParts.size < 2 || endParts.size < 2) {
        return ""
    }

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

    return if (rounded % 1.0 == 0.0) {
        rounded.toInt().toString()
    } else {
        rounded.toString()
    }
}