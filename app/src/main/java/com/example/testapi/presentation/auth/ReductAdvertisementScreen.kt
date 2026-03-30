package com.example.testapi.presentation.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.testapi.R
import com.example.testapi.presentation.widget.CustomTextButton
import com.example.testapi.presentation.widget.CustomTopAppBar
import com.example.testapi.ui.theme.Blue
import com.example.testapi.ui.theme.Grey
import com.example.testapi.ui.theme.GreyForCorner
import com.example.testapi.ui.theme.InputGrey
import com.example.testapi.ui.theme.Inter
import com.example.testapi.ui.theme.SupportText
import com.example.testapi.ui.theme.White
import com.example.testapi.ui.theme.Yellow

@Preview(showBackground = true)
@Composable
fun ReductAdvertisementScreen() {
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
                CustomTopAppBar(text = "Редактирование")
            },
            bottomBar = {
                // твой CustomBottomBar
            },
            content = { paddingValues ->
                Content(paddingValues = paddingValues)
            }
        )
    }
}

@Composable
private fun Content(
    paddingValues: PaddingValues
) {
    val wantedJob = remember { mutableStateOf("") }
    val address = remember { mutableStateOf("") }
    val timeStart = remember { mutableStateOf("") }
    val timeEnd = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val salary = remember { mutableStateOf("") }
    val isUrgent = remember { mutableStateOf(false) }
    val car = remember { mutableStateOf(false) }
    val xp = remember { mutableStateOf("") }
    val age = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }

    val isUrgentColor = if (isUrgent.value) Yellow else Grey
    val carColor = if (car.value) Color.Red else Grey

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize()
            .padding(horizontal = 25.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = White,
                    shape = RoundedCornerShape(32.dp)
                )
                .padding(vertical = 15.dp, horizontal = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Желаемая должность",
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                InputField(text = wantedJob, hintText = "Напишите название подработки")
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Адрес",
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                InputField(text = wantedJob, hintText = "Напишите адрес подработки")
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Время",
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                AdTime(timeStart = timeStart, timeEnd = timeEnd)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Дата",
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                DateInputField(text = date, hintText = "ДД.ММ.ГГГГ")
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Зарплата",
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                InputField(text = salary, hintText = "Укажите заработную плату")
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CircleIcon(route = R.drawable.ic_lightning, color = isUrgentColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Срочно",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Inter
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Switch(
                        checked = isUrgent.value,
                        onCheckedChange = { isUrgent.value = it },
                        modifier = Modifier.scale(0.8f),
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = White,
                            checkedTrackColor = Blue,
                            uncheckedThumbColor = White,
                            uncheckedTrackColor = Grey
                        )
                    )
                }
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    CircleIcon(route = R.drawable.ic_car, color = carColor)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Авто",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        fontFamily = Inter
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Switch(
                        checked = car.value,
                        onCheckedChange = { car.value = it },
                        modifier = Modifier.scale(0.8f),
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = White,
                            checkedTrackColor = Blue,
                            uncheckedThumbColor = White,
                            uncheckedTrackColor = Grey
                        )
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Опыт работы",
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                WorkExperience(xp = xp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Возраст",
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                AgeSelectionButtons(age = age)
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Описание",
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Inter
                )
                Spacer(modifier = Modifier.height(10.dp))
                InputDescriptionField(text = description, hintText = "Напишите описание подработки")
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        CustomTextButton(text = "Сохранить изменения", color = Blue, onClick = {})
        Spacer(modifier = Modifier.height(30.dp))

    }
}


@Composable
private fun InputField(
    text: MutableState<String>,
    hintText: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .background(
                color = InputGrey,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        BasicTextField(
            value = text.value,
            onValueChange = { text.value = it },
            singleLine = true,
            textStyle = TextStyle(color = SupportText, fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp)
                .padding(start = 16.dp, end = 16.dp),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (text.value.isEmpty()) {
                        Text(
                            text = hintText,
                            color = SupportText.copy(alpha = 0.6f),
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Composable
private fun DateInputField(
    text: MutableState<String>,
    hintText: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp)
            .background(
                color = InputGrey,
                shape = RoundedCornerShape(15.dp)
            )
    ) {

        BasicTextField(
            value = text.value,
            onValueChange = { input ->
                text.value = input.filter { it.isDigit() }.take(8)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),
            visualTransformation = DateVisualTransformation(),
            textStyle = TextStyle(
                color = SupportText,
                fontSize = 16.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (text.value.isEmpty()) {
                        Text(
                            text = hintText,
                            color = SupportText.copy(alpha = 0.6f),
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}



@Composable
private fun InputDescriptionField(
    text: MutableState<String>,
    hintText: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = InputGrey,
                shape = RoundedCornerShape(15.dp)
            )
    ) {
        BasicTextField(
            value = text.value,
            onValueChange = { text.value = it },
            singleLine = false,
            maxLines = Int.MAX_VALUE,
            textStyle = TextStyle(color = SupportText, fontSize = 16.sp),
            modifier = Modifier
                .fillMaxWidth()
                .height(47.dp)
                .wrapContentHeight()
                .padding(start = 16.dp, end = 16.dp),
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (text.value.isEmpty()) {
                        Text(
                            text = hintText,
                            color = SupportText.copy(alpha = 0.6f),
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}


@Composable
private fun AdTime(
    timeStart: MutableState<String>,
    timeEnd: MutableState<String>
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .background(
                    color = InputGrey,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            BasicTextField(
                value = timeStart.value,
                onValueChange = { input ->
                    // Храним только цифры
                    timeStart.value = input.filter { it.isDigit() }.take(4)
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                visualTransformation = TimeVisualTransformation(),
                textStyle = TextStyle(
                    color = SupportText,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(47.dp)
                    .padding(horizontal = 16.dp),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (timeStart.value.isEmpty()) {
                            Text(
                                text = "ЧЧ:ММ",
                                color = SupportText.copy(alpha = 0.6f),
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
        Spacer(modifier = Modifier.width(10.dp))
        Box(
            modifier = Modifier
                .weight(1f)
                .height(40.dp)
                .background(
                    color = InputGrey,
                    shape = RoundedCornerShape(15.dp)
                )
        ) {
            BasicTextField(
                value = timeEnd.value,
                onValueChange = { input ->
                    // Храним только цифры
                    timeEnd.value = input.filter { it.isDigit() }.take(4)
                },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                ),
                visualTransformation = TimeVisualTransformation(),
                textStyle = TextStyle(
                    color = SupportText,
                    fontSize = 16.sp
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(47.dp)
                    .padding(horizontal = 16.dp),
                decorationBox = { innerTextField ->
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (timeEnd.value.isEmpty()) {
                            Text(
                                text = "ЧЧ:ММ",
                                color = SupportText.copy(alpha = 0.6f),
                                fontSize = 16.sp
                            )
                        }
                        innerTextField()
                    }
                }
            )
        }
    }
}

@Composable
private fun CircleIcon(
    route: Int,
    color: Color,
) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
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
            painter = painterResource(id = route),
            contentDescription = null,
            tint = color,
            modifier = Modifier.size(15.dp)
        )
    }
}

@Composable
private fun WorkExperience(
    xp: MutableState<String>
) {

    val options = listOf("Нет опыта", "От 1 года", "От 3 лет")
    var expanded by remember { mutableStateOf(false) }
    var selected by remember { mutableStateOf("Выберите опыт работы") }

    val textColor =
        if (selected == "Выберите опыт работы")
            SupportText.copy(alpha = 0.6f)
        else
            Color.Black

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Box(
            contentAlignment = Alignment.CenterStart,
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .background(color = InputGrey, shape = RoundedCornerShape(15.dp))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = { expanded = true }
                )
                .padding(start = 10.dp)
        ) {
            Text(
                text = selected,
                color = textColor,
                fontSize = 16.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Normal
            )
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .background(Color.White, RoundedCornerShape(20.dp)),
            shape = RoundedCornerShape(20.dp),
            containerColor = White,
            tonalElevation = 8.dp
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = option,
                            fontWeight = if (selected == option)
                                FontWeight.Bold
                            else FontWeight.Normal
                        )
                    },
                    onClick = {
                        selected = option
                        xp.value = option
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
private fun AgeSelectionButtons(
    age: MutableState<String>
) {
    val selectIndex = remember { mutableStateOf(0) }
    val interactionSource = remember { MutableInteractionSource() }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    selectIndex.value = 1
                    age.value = "Старше 14 лет"
                }
                .height(20.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = if (selectIndex.value == 1) R.drawable.ic_select
                    else R.drawable.ic_un_select
                ),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Старше 14 лет",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable (
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    selectIndex.value = 2
                    age.value = "Старше 16 лет"
                }
                .height(20.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = if (selectIndex.value == 2) R.drawable.ic_select
                    else R.drawable.ic_un_select
                ),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Старше 16 лет",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Normal
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .clickable (
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    selectIndex.value = 3
                    age.value = "Старше 18 лет"
                }
                .height(20.dp)
        ) {
            Icon(
                painter = painterResource(
                    id = if (selectIndex.value == 3) R.drawable.ic_select
                    else R.drawable.ic_un_select
                ),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Старше 18 лет",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = Inter,
                fontWeight = FontWeight.Normal
            )
        }
    }
}