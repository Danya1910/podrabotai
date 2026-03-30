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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.testapi.R
import com.example.testapi.domain.model.Message
import com.example.testapi.presentation.viewModels.AdvertisementViewModel
import com.example.testapi.presentation.viewModels.ChatViewModel
import com.example.testapi.presentation.widget.AdvertisementForChat
import com.example.testapi.presentation.widget.CustomTopAppBar
import com.example.testapi.presentation.widget.CustomTopAppBarForDetailed
import com.example.testapi.presentation.widget.MineMessage
import com.example.testapi.presentation.widget.StrangerMessage
import com.example.testapi.ui.theme.BackgroundForInputField
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.ChatBlue
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.SupportText
import com.example.testapi.ui.theme.White
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun ChatScreen(
    viewModel: ChatViewModel,
    adViewModel: AdvertisementViewModel,
    navController: NavController,
    penpalId: Int,
    jobId: Int = -1
) {
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
                    text = "Чат",
                )
            },
            containerColor = Color.Transparent,
            content = { paddingValues ->
                Content(
                    viewModel = viewModel,
                    adViewModel = adViewModel,
                    navController = navController,
                    paddingValues = paddingValues,
                    penpalId = penpalId,
                    jobId = jobId
                )
            }
        )
    }
}

@Composable
private fun Content(
    viewModel: ChatViewModel,
    adViewModel: AdvertisementViewModel,
    navController: NavController,
    paddingValues: PaddingValues,
    penpalId: Int,
    jobId: Int
) {

    LaunchedEffect(penpalId, jobId) {
        if (jobId != -1) {
            viewModel.createChat(penpalId, jobId)
            Log.d("Chat Debug", "CreateChat called from ChatScreen")
        } else {
            viewModel.loadHistory(penpalId)
            Log.d("Chat Debug", "LoadHistory called from ChatScreen")

        }
    }

    val createState = viewModel.createChatState.value

    LaunchedEffect(createState.error == "HTTP 406 NOT ACCEPTABLE") {
        if (createState.error == "HTTP 406 NOT ACCEPTABLE") {
            Log.d("ChatDebug", createState.error)
            viewModel.loadHistory(penpalId)
        }
    }


    val inputFormatter = DateTimeFormatter.ofPattern(
        "EEE, dd MMM yyyy HH:mm:ss z",
        Locale.ENGLISH
    )

    val state = viewModel.getChatHistoryState.value

    LaunchedEffect(state.getChatHistory?.chat?.jobId) {
        state.getChatHistory?.chat?.jobId?.let {
            adViewModel.loadDetailedAdvertisement(it)
            Log.d("Chat Debug", viewModel.createChatState.value.createChat?.message ?: "NONE")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(bottom = 55.dp)
    ) {
        NameHat(name = state.getChatHistory?.chat?.name ?: "Unknown", navController = navController)
        Spacer(modifier = Modifier.height(15.dp))
        adViewModel.getDetailedAdvertisementState.value.getDetailedAdvertisement?.let {
            AdvertisementForChat(ad = it, viewModel = adViewModel)
        }
        val messages = state.getChatHistory?.messages ?: emptyList()
        val listState = rememberLazyListState()

        if (messages.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Сообщений пока нет",
                    color = SupportText,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Inter
                )
            }
        } else if (viewModel.getChatsState.value.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            val chatItems = generateChatItems(messages)
            LaunchedEffect(chatItems.size) {
                if (chatItems.isNotEmpty()) {
                    kotlinx.coroutines.delay(150)
                    listState.animateScrollToItem(chatItems.lastIndex)
                }
            }
            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 10.dp)
            ) {
                items(chatItems) { item ->
                    when (item) {
                        is ChatItem.DateSeparator -> {
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                horizontalArrangement = Arrangement.Center,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(24.dp)
                            ) {
                                DateInfo(date = item.date)
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }

                        is ChatItem.MessageItem -> {
                            val message = item.message
                            if (message.senderId != penpalId) {
                                MineMessage(
                                    text = message.text,
                                    time = formatChatTime(message.createdAt)
                                )
                            } else {
                                StrangerMessage(
                                    text = message.text,
                                    time = formatChatTime(message.createdAt)
                                )
                            }
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                }
            }
        }
    }
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier
            .fillMaxSize()
            .height(53.dp)
            .padding(horizontal = 10.dp)
            .padding(bottom = 10.dp)
    ) {
        CustomInputField(viewModel = viewModel, penpalId = penpalId)
    }
}

fun formatChatTime(date: String): String {

    val inputFormatter = DateTimeFormatter.ofPattern(
        "EEE, dd MMM yyyy HH:mm:ss z",
        Locale.ENGLISH
    )

    val outputFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val parsedDate = ZonedDateTime.parse(date, inputFormatter)

    return parsedDate.format(outputFormatter)
}

fun formatChatDate(createdAt: String): String {
    val inputFormatter = DateTimeFormatter.ofPattern(
        "EEE, dd MMM yyyy HH:mm:ss z",
        Locale.ENGLISH
    )

    val russianOutputFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", Locale("ru"))
    val currentYearOutputFormatter = DateTimeFormatter.ofPattern("dd MMMM", Locale("ru"))
    val yearOutputFormatter = DateTimeFormatter.ofPattern("yyyy")

    val parsedDate = ZonedDateTime.parse(createdAt, inputFormatter)
    val messageDate = parsedDate.toLocalDate()

    val today = LocalDate.now()
    val yesterday = today.minusDays(1)

    val todayYear = today.format(yearOutputFormatter)
    val messageYear = messageDate.format(yearOutputFormatter)

    val date: String;

    if (todayYear == messageYear)
        date = parsedDate.format((currentYearOutputFormatter))
    else
        date = parsedDate.format((russianOutputFormatter))

    return when (messageDate) {
        today -> "Сегодня"
        yesterday -> "Вчера"
        else -> date
    }
}

@Composable
private fun DateInfo(
    date: String
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .height(24.dp)
            .border(
                width = 1.dp,
                color = White,
                shape = RoundedCornerShape(20.dp)
            )
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 8.dp, vertical = 5.dp)
    ) {
        Text(
            text = date,
            color = White,
            fontSize = 13.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Inter
        )
    }
}

@Composable
private fun NameHat(
    name: String,
    navController: NavController
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxWidth()
            .height(53.dp)
            .background(color = White, shape = RoundedCornerShape(200.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 10.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_left_arrow),
                contentDescription = null,
                tint = Blue,
                modifier = Modifier
                    .clickable {
                        navController.popBackStack()
                    }
            )
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = Inter,
                color = Color.Black
            )
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                painter = painterResource(R.drawable.ic_person),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}

@Composable
private fun CustomInputField(
    viewModel: ChatViewModel,
    penpalId: Int
) {

    val text = remember { mutableStateOf("") }

    LaunchedEffect(viewModel.sendMessageState.value.isSuccessful) {
        if (viewModel.sendMessageState.value.isSuccessful) {
            text.value = ""
            viewModel.resetSendMessageFlag()
            viewModel.loadHistory(penpalId = penpalId)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(53.dp)
            .background(
                color = BackgroundForInputField,
                shape = RoundedCornerShape(200.dp)
            )

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 14.dp, end = 11.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_clip),
                contentDescription = null,
                tint = Blue
            )
            Spacer(modifier = Modifier.width(8.dp))
            Row(
                modifier = Modifier
                    .weight(1f)
            ) {
                CustomField(text = text)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(36.dp)
                    .clickable { viewModel.sendMessage(penpalId = penpalId, text = text.value) }
                    .background(color = Blue, shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_arrow_up),
                    contentDescription = null,
                    tint = White
                )
            }
        }
    }
}

@Composable
private fun CustomField(
    text: MutableState<String>
) {
    Box(
        modifier = Modifier
            .height(45.dp)
            .fillMaxWidth()
            .background(
                color = Color(0x3386BAF2),
                shape = RoundedCornerShape(200.dp),
            )
            .border(
                width = 1.dp,
                color = Color(0xFF86BAF2),
                shape = RoundedCornerShape(200.dp)
            )
    ) {
        BasicTextField(
            value = text.value,
            onValueChange = { text.value = it },
            singleLine = true,
            textStyle = TextStyle(
                color = SupportText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Inter
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(45.dp)
                .padding(start = 16.dp, end = 16.dp),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (text.value.isEmpty()) {
                        Text(
                            text = "Сообщение...",
                            color = SupportText.copy(alpha = 0.3f),
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = Inter
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

// Создаём вспомогательный класс
sealed class ChatItem {
    data class MessageItem(val message: Message) : ChatItem()
    data class DateSeparator(val date: String) : ChatItem()
}

// Функция для генерации списка с датами
fun generateChatItems(messages: List<Message>): List<ChatItem> {
    val items = mutableListOf<ChatItem>()
    var lastDate: LocalDate? = null
    val inputFormatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss z", Locale.ENGLISH)

    for (msg in messages) {
        val msgDate = ZonedDateTime.parse(msg.createdAt, inputFormatter).toLocalDate()
        if (msgDate != lastDate) {
            items.add(ChatItem.DateSeparator(formatChatDate(msg.createdAt)))
            lastDate = msgDate
        }
        items.add(ChatItem.MessageItem(msg))
    }
    return items
}