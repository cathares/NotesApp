package com.example.notesapp.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class Note(
    val uuid: String,
    val title: String,
    val content: String
)

@Serializable
data class NoteList(
    val items: List<NoteTiny>,
    val count: Int
)

@Serializable
data class NoteTiny(
    val uuid: String,
    val title: String
)
