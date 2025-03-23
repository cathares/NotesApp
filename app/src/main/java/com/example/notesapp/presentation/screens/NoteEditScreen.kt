package com.example.notesapp.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
import com.example.notesapp.presentation.ui.theme.nickainley

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteEditScreen(
    viewModel: ViewModel,
    onSaveClick: () -> Unit,
    onBackClick: () -> Unit
    ) {
    val uiState by viewModel.noteUIState.collectAsState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFF460F0C))
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = painterResource(R.drawable.note_edit_pic),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
        )
        Box(Modifier.align(Alignment.Center)) {

            Image(
                modifier = Modifier.fillMaxWidth().padding(start = 16.dp),
                painter = painterResource(R.drawable.note_background),
                contentScale = ContentScale.FillWidth,
                contentDescription = null,
            )
            Column(
                modifier = Modifier.padding(top = 70.dp, start = 60.dp, end = 20.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        modifier = Modifier
                            .padding(top=12.dp)
                            .clickable {
                                onBackClick()
                                viewModel.clearState()
                                       },
                        text = "< заметки",
                        fontFamily = nickainley,
                        fontSize = 24.sp,
                        color = Color(0xFF5F0E0E)
                    )
                    OutlinedTextField(
                        value = uiState.title,
                        onValueChange = { newTitle -> viewModel.updateNoteTitle(newTitle) },
                        textStyle = LocalTextStyle.current.copy(
                            fontFamily = misterBrush,
                            fontSize = 20.sp,
                            color = Color.Black.copy(alpha = 0.7f),
                            textAlign = TextAlign.Right
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Transparent),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        placeholder = {
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Название заметки",
                                fontFamily = misterBrush,
                                fontSize = 20.sp,
                                color = Color.Black.copy(alpha = 0.7f),
                                textAlign = TextAlign.Right,
                                softWrap = false
                            )
                        },
                    )
                }
            }
            OutlinedTextField(
                modifier = Modifier.matchParentSize()
                    .padding(50.dp, 130.dp).padding(start = 40.dp),
                value = uiState.text,
                onValueChange = { updatedText -> viewModel.updateNoteText(updatedText) },
                textStyle = LocalTextStyle.current.copy(
                    fontFamily = nickainley,
                    fontSize = 20.sp,
                    color = Color(0xFF4D0A0A),
                    textAlign = TextAlign.Left
                ),
                shape = RectangleShape,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFF4D0A0A),
                    unfocusedBorderColor = Color(0xFF4D0A0A),
                    cursorColor = Color(0xFF4D0A0A)
                )
            )
        }
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            painter = painterResource(R.drawable.paper_note_edit_bottom),
            contentScale = ContentScale.FillWidth,
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(20.dp)
                .clickable {
                    if (viewModel.noteUIState.value.isNew) {
                        viewModel.createNote(uiState.title, uiState.text)
                    }
                    else {
                        viewModel.editNote()
                    }
                    onSaveClick()
                }
            ,
            text = "СОХРАНИТЬ",
            fontFamily = misterBrush,
            fontSize = 45.sp,
            color = Color(0xFF460F0C)
        )
    }
}

@Composable
@Preview
fun NoteEditScreenPreview() {
}