package com.example.notesapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.R
import com.example.notesapp.presentation.ViewModel
import com.example.notesapp.presentation.ui.theme.misterBrush

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: ViewModel,
    onClick: () -> Unit,
) {
    var login by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var repeatPassword by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.gold),
            contentDescription = null
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.top_paper),
            contentDescription = null
        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.bottom_paper),
            contentDescription = null
        )
    }
    Column(
        modifier = Modifier.fillMaxSize().padding(top=50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "имя пользователя",
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontFamily = misterBrush,
            color = Color.White
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = login,
            onValueChange = { updatedName -> login = updatedName },
            shape = RoundedCornerShape(50),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.textfield),
                focusedContainerColor = colorResource(R.color.textfield),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "пароль",
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontFamily = misterBrush,
            color = Color.White
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = password,
            onValueChange = { updatedPassword -> password = updatedPassword },
            shape = RoundedCornerShape(50),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.textfield),
                focusedContainerColor = colorResource(R.color.textfield),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        Text(
            modifier = Modifier.padding(top = 10.dp),
            text = "повторите пароль",
            textAlign = TextAlign.Center,
            fontSize = 40.sp,
            fontFamily = misterBrush,
            color = Color.White
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
            value = repeatPassword,
            onValueChange = { updatedRepeatPassword -> repeatPassword = updatedRepeatPassword },
            shape = RoundedCornerShape(50),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colorResource(R.color.textfield),
                focusedContainerColor = colorResource(R.color.textfield),
                unfocusedTextColor = Color.White,
                focusedTextColor = Color.White,
                cursorColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Text(
            modifier = Modifier.padding(20.dp).clickable {
                viewModel.register(login, password)
                onClick()
            },
            text = "зарегистрироваться",
            color = Color(0xFF580F0F),
            fontFamily = misterBrush,
            fontSize = 35.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun RegisterScreenPreview() {
}