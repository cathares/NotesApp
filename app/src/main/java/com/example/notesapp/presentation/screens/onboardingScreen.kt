package com.example.notesapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.R
import com.example.notesapp.presentation.ui.theme.misterBrush

@Composable
fun OnboardingScreen(
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    Box(Modifier
        .fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.onboarding_background),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.paper),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
            Column(
                Modifier.padding(bottom = 45.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.clickable { onLoginClick() },
                    text = "ВОЙТИ",
                    color = Color(0xFF580F0F),
                    fontFamily = misterBrush,
                    fontSize = 65.sp,
                    textAlign = TextAlign.Center
                )
                Spacer(
                    modifier = Modifier.height(10.dp)
                )
                Text(
                    modifier = Modifier.clickable { onRegisterClick() },
                    text = "регистрация",
                    color = Color(0xFF580F0F),
                    fontFamily = misterBrush,
                    fontSize = 35.sp,
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

@Preview
@Composable
fun OnboardingScreenPreview() {
}