package com.example.notesapp.presentation.state

data class NoteUIState(
    val isLoading: Boolean = false,
    val isNew: Boolean = true,
    val id: String = "",
    val text: String = "",
    val title: String = "",
    val count: Int = 0
)
