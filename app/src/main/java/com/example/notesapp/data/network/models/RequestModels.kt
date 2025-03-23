package com.example.notesapp.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val login: String,
    val password: String
)

@Serializable
data class LoginRequest(
    val login: String,
    val password: String
)

@Serializable
data class CreateRequest(
    val title: String,
    val content: String
)

@Serializable
data class UpdateRequest(
    val title: String,
    val content: String
)