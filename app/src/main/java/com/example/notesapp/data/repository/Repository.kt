package com.example.notesapp.data.repository

import com.example.notesapp.data.network.NetworkResult
import com.example.notesapp.data.network.models.LoginResponse
import com.example.notesapp.data.network.models.Note
import com.example.notesapp.data.network.models.NoteList
import com.example.notesapp.data.network.models.RegisterResponse

interface Repository {
    suspend fun getNotes(): NetworkResult<NoteList>
    suspend fun getNote(id: String): NetworkResult<Note>
    suspend fun register(login: String, password: String): NetworkResult<RegisterResponse>
    suspend fun login(login: String, password: String): NetworkResult<LoginResponse>
    suspend fun createNote(title: String, content: String): NetworkResult<Note>
    suspend fun updateNote(id: String, title: String, content: String): NetworkResult<Note>
    suspend fun deleteNote(id: String): NetworkResult<Unit>
}