package com.example.notesapp.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponse(
    val token: String
)

@Serializable
data class RegisterResponse(
    val id: Int
)
