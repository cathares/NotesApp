package com.example.notesapp.presentation.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notesapp.R
import com.example.notesapp.presentation.ViewModel
import com.example.notesapp.presentation.ui.theme.misterBrush


@Composable
fun NotesListScreen(
    viewModel: ViewModel,
    onNoteClick: () -> Unit,
    onExitClick: () -> Unit,
    onNewNoteClick: () -> Unit,
) {
    val noteListUIState = viewModel.noteListUIState.collectAsState()
    val count = noteListUIState.value.count
    val notes = noteListUIState.value.notes
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(R.color.background)),
        contentAlignment = Alignment.Center
    ) {
        // Фоновые изображения
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.list_flowers_top),
            contentDescription = null
        )
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomEnd),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.list_flowers_bottom),
            contentDescription = null
        )
        // Основной контент
        Box(
            modifier = Modifier
                .padding(vertical = 80.dp, horizontal = 40.dp)
                .padding(bottom = 60.dp)
                .fillMaxSize()
                .background(color = Color.White.copy(alpha = 0.74f))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // Заголовок и информация
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier.clickable { onExitClick() },
                        text = "список\nзаметок",
                        color = Color.Black,
                        fontFamily = misterBrush,
                        fontSize = 45.sp,
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = "Всего: ${count} заметок",
                        color = Color(0xFF444444),
                        fontSize = 24.sp,
                        fontFamily = misterBrush,
                        modifier = Modifier.padding(3.dp)
                    )

                }

                // LazyColumn с заметками
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    item { DashedDivider() }
                    items(notes) { note ->
                        ListElement(note.title) {
                            viewModel.loadNote(note.uuid)
                            onNoteClick()
                        }
                    }
                }

                // Кнопка "новая заметка"
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onNewNoteClick() },
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        HorizontalDivider(
                            thickness = 2.dp,
                            color = colorResource(R.color.divider)
                        )
                        Text(
                            modifier = Modifier.padding(5.dp),
                            text = "новая заметка",
                            color = Color.Black,
                            fontFamily = misterBrush,
                            fontSize = 30.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
        Image(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            contentScale = ContentScale.FillWidth,
            painter = painterResource(R.drawable.bottom_paper),
            contentDescription = null
        )
        Text(
            modifier = Modifier.align(Alignment.BottomCenter).padding(15.dp).clickable {
                onExitClick()
            },
            text = "ВЫЙТИ",
            color = Color(0xFF580F0F),
            fontFamily = misterBrush,
            fontSize = 45.sp,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ListElement(
    title: String,
    onClick: () -> Unit,
){
    Box(modifier = Modifier.clickable { onClick() }) {
        Column(modifier = Modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                color = Color.Black,
                fontFamily = misterBrush,
                fontSize = 30.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(3.dp)
            )
            DashedDivider()
        }
    }


}
@Composable
fun DashedDivider(
    color: Color = colorResource(R.color.divider),
    strokeWidth: Float = 5f,
    dashLength: Float = 25f,
    gapLength: Float = 25f
) {
    Canvas(
        modifier = Modifier
            .height(strokeWidth.dp)
            .fillMaxWidth()
    ) {
        val pathEffect = PathEffect.dashPathEffect(
            intervals = floatArrayOf(dashLength, gapLength),
            phase = 0f
        )

        drawLine(
            color = color,
            start = Offset(0f, strokeWidth / 2),
            end = Offset(size.width, strokeWidth / 2),
            strokeWidth = strokeWidth,
            pathEffect = pathEffect
        )
    }
}

@Composable
@Preview
fun NotesListScreenPreview() {
}