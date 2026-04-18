package com.example.testapi.presentation.auth

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.testapi.R
import com.example.testapi.domain.model.BottomNavItem
import com.example.testapi.domain.model.GetChatsResponse
import com.example.testapi.presentation.navigation.Screen
import com.example.testapi.presentation.viewModels.ChatViewModel
import com.example.testapi.presentation.widget.CustomBottomBar
import com.example.testapi.presentation.widget.CustomTopAppBar
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.ChatBlue
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.White
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun EmployerChatsScreen(
    viewModel: ChatViewModel,
    navController: NavController,
) {

    val items = listOf(
        BottomNavItem(route = Screen.EmployerWork.route, icon = R.drawable.ic_work),
        BottomNavItem(route = Screen.MyAdvertisements.route, icon = R.drawable.ic_megaphone),
        BottomNavItem(route = Screen.EmployerChats.route, icon = R.drawable.ic_message),
        BottomNavItem(route = Screen.EmployerProfile.route, icon = R.drawable.ic_profile)
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.background),
            contentDescription = null,
            modifier = Modifier.matchParentSize(),
            contentScale = ContentScale.Crop
        )
        Scaffold(
            topBar = {
                CustomTopAppBar(
                    text = "Чаты",
                )
            },
            bottomBar = {
                CustomBottomBar(
                    items = items,
                    currentRoute = currentRoute,
                    onItemClick = { item ->
                        navController.navigate(item.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    }
                )
            },
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
    viewModel: ChatViewModel,
    navController: NavController,
    paddingValues: PaddingValues,
) {

    val state = viewModel.getChatsState.value

    LaunchedEffect(Unit) {
        viewModel.loadChats()
    }

    if(state.isLoading) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        color = Color.Black.copy(alpha = 0.3f),
                        shape = RoundedCornerShape(32.dp)
                    )
            ) {
                CircularProgressIndicator(
                    color = Blue,
                    strokeWidth = 7.dp,
                    modifier = Modifier
                        .size(50.dp)
                )
            }
        }
    }

    if(state.chats.isNullOrEmpty()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Чатов пока нет",
                    color = Color.Black,
                    fontFamily = Inter,
                    fontWeight = FontWeight.Medium,
                    fontSize = 18.sp
                )
            }
        }
    }

    LazyColumn(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 30.dp)
            .fillMaxSize()
    ) {

        state.error?.let {
            item {
                Text(
                    text = it,
                    color = Color.Red
                )
            }
        }

        state.chats?.let { chats ->
            items(chats) { chat ->
                Chat(chat = chat, navController = navController)
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}


@Composable
private fun Chat(
    chat: GetChatsResponse,
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .height(67.dp)
            .fillMaxWidth()
            .clickable { navController.navigate(Screen.Chat.passArgs(penpalId = chat.penpalId)) }
            .drawBehind {
                val strokeWidth = 1.dp.toPx()

                drawLine(
                    color = White,
                    start = androidx.compose.ui.geometry.Offset(10f, 0f),
                    end = androidx.compose.ui.geometry.Offset(size.width, 0f),
                    strokeWidth = strokeWidth
                )

                drawLine(
                    color = White,
                    start = androidx.compose.ui.geometry.Offset(10f, size.height),
                    end = androidx.compose.ui.geometry.Offset(size.width, size.height),
                    strokeWidth = strokeWidth
                )
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_avatar),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.size(55.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))

            Column(
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(top = 8.dp, end = 10.dp)
                    .weight(1f)
            ) {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(16.dp)
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(20.dp)
                        )
                        .padding(horizontal = 5.dp)
                ) {
                    Text(
                        text = chat.penpalName,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = Inter,
                        color = Color.Black
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = chat.lastMessage?.text ?: "",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = Inter,
                    color = White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.wrapContentWidth()
            ) {
                Text(
                    text = TimeToDomain(time = chat.lastMessage?.createdAt ?: ""),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Light,
                    fontFamily = Inter,
                    color = White
                )
                Spacer(modifier = Modifier.height(5.dp))
                if (chat.unreadMessages != 0) {
                    Circle(text = chat.unreadMessages.toString())
                }
            }
        }
    }
}

@Composable
private fun Circle(
    text: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(24.dp)
            .border(
                width = 1.dp,
                color = White,
                shape = CircleShape
            )
            .background(
                color = ChatBlue, shape = CircleShape
            )
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Inter,
            color = White
        )
    }
}

private fun TimeToDomain(
    time: String
) : String {
    return try {
        val formatter = DateTimeFormatter.ofPattern(
            "EEE, dd MMM yyyy HH:mm:ss z",
            Locale.ENGLISH
        )
        val dateTime = ZonedDateTime.parse(time, formatter)

        dateTime.format(DateTimeFormatter.ofPattern("HH:mm"))
    } catch (e: Exception) {
        Log.d("ChatsScreenDebug", "error: ${e.message}")
        ""
    }
}