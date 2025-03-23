package com.example.notesapp.presentation.state

import com.example.notesapp.data.network.models.NoteTiny

data class NoteListUIState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val notes: List<NoteTiny> = emptyList(),
    val count: Int = 0
)