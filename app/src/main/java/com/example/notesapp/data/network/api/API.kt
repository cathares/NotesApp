package com.example.notesapp.data.network.api

import com.example.notesapp.data.network.models.CreateRequest
import com.example.notesapp.data.network.models.LoginRequest
import com.example.notesapp.data.network.models.LoginResponse
import com.example.notesapp.data.network.models.Note
import com.example.notesapp.data.network.models.NoteList
import com.example.notesapp.data.network.models.RegisterRequest
import com.example.notesapp.data.network.models.RegisterResponse
import com.example.notesapp.data.network.models.UpdateRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface API {
    @GET("notes")
    suspend fun fetchList(
        @Header("Authorization") token: String
    ): Response<NoteList>

    @GET("notes/{id}")
    suspend fun fetchNote(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<Note>

    @PUT("notes/{id}")
    suspend fun updateNote(
        @Header("Authorization") token: String,
        @Path("id") id: String,
        @Body request: UpdateRequest
    ): Response<Note>

    @DELETE("notes/{id}")
    suspend fun deleteNote(
        @Header("Authorization") token: String,
        @Path("id") id: String
    ): Response<Unit>

    @POST("notes")
    suspend fun createNote(
        @Header("Authorization") token: String,
        @Body request: CreateRequest
    ): Response<Note>

    @POST("register")
    suspend fun register(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

    @POST("login")
    suspend fun login(
        @Body request: LoginRequest
    ): Response<LoginResponse>

}