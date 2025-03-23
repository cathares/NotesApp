package com.example.notesapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.notesapp.R
import com.example.notesapp.presentation.ViewModel
import com.example.notesapp.presentation.ui.theme.misterBrush

@Composable
fun ExitScreen(
    viewModel: ViewModel,
    onClick: () -> Unit
    ) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background)),
        contentAlignment = Alignment.Center
    ) {}
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.exit_paper_top),
            contentDescription = null
        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.exit_paper_bottom),
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
            painter = painterResource(R.drawable.exit_flowers_top),
            contentDescription = null
        )
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.exit_flowers_bottom),
            contentDescription = null
        )
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.clickable {
                onClick()
                viewModel.exit()
            },
            text = "ВЫЙТИ С \nДАННОГО\nУСТРОЙСТВА",
            color = Color(0xFF580F0F),
            fontFamily = misterBrush,
            fontSize = 45.sp,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier.clickable {
                onClick()
                viewModel.exit()
            },
            text = "ВЫЙТИ СО\nВСЕХ устройств",
            color = Color(0xFF580F0F),
            fontFamily = misterBrush,
            fontSize = 45.sp,
            textAlign = TextAlign.Center
        )
    }
}
@Composable
@Preview
fun ExitScreenPreview() {
}