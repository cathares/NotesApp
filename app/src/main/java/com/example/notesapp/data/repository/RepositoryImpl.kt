package com.example.notesapp.data.repository

import android.util.Log
import com.example.notesapp.data.TokenManager
import com.example.notesapp.data.network.NetworkResult
import com.example.notesapp.data.network.api.API
import com.example.notesapp.data.network.models.CreateRequest
import com.example.notesapp.data.network.models.LoginRequest
import com.example.notesapp.data.network.models.LoginResponse

import com.example.notesapp.data.network.models.Note
import com.example.notesapp.data.network.models.NoteList
import com.example.notesapp.data.network.models.RegisterRequest
import com.example.notesapp.data.network.models.RegisterResponse
import com.example.notesapp.data.network.models.UpdateRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class RepositoryImpl(
    private val api: API,
    private val dispatcher: CoroutineDispatcher,
    private val tokenManager: TokenManager
): Repository {
    override suspend fun getNotes(): NetworkResult<NoteList> {
        Log.e("getNotes", "ЗДесь кто-нибудь есть?")
        return withContext(dispatcher) {
            try {
                val token = tokenManager.getToken() ?:
                return@withContext NetworkResult.Error("Token is null")

                val response = api.fetchList(token)
                Log.e("getNotes", "${response.code()}?")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResult.Success(body)
                    }
                    else {
                        NetworkResult.Error("Response body is null")
                    }
                }
                else {
                    NetworkResult.Error("HTTP error: ${response.code()}")
                }
            }
            catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error while fetching notelist")
                }
            }
        }

    override suspend fun getNote(id: String): NetworkResult<Note> {
        return withContext(dispatcher) {
            try {
                val token = tokenManager.getToken() ?:
                return@withContext NetworkResult.Error("Token is null")

                val response = api.fetchNote(token, id)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResult.Success(body)
                    }
                    else {
                        NetworkResult.Error("Response body is null")
                    }
                }
                else {
                    NetworkResult.Error("HTTP error: ${response.code()}")
                }
            }
            catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error while fetching note")
            }
        }
    }

    override suspend fun register(login: String, password: String): NetworkResult<RegisterResponse> {
        return withContext(dispatcher) {
            try {
                val response = api.register(RegisterRequest(login, password))

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResult.Success(body)
                    }
                    else {
                        NetworkResult.Error("Response body is null")
                    }
                }
                else {
                    NetworkResult.Error("HTTP error: ${response.code()}")
                }
            }
            catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error while registration")
            }
        }
    }

    override suspend fun login(login: String, password: String): NetworkResult<LoginResponse> {
        Log.e("логин", "старт")
        return withContext(dispatcher) {
            Log.e("логин", "диспетчер")
            try {
                val response = api.login(LoginRequest(login, password))
                Log.e("логин", "${response.code()}")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        tokenManager.setToken(body.token)
                        NetworkResult.Success(body)
                    }
                    else {
                        NetworkResult.Error("Response body is null")
                    }
                }
                else {
                    NetworkResult.Error("HTTP error: ${response.code()}")
                }
            }
            catch (e: Exception) {
                Log.e("логин", "${e.message}")
                NetworkResult.Error(e.message ?: "Unknown error while login")
            }
        }
    }

    override suspend fun createNote(title: String, content: String): NetworkResult<Note> {
        Log.e("create", "старт?")
        return withContext(dispatcher) {
            try {
                val token = tokenManager.getToken() ?:
                return@withContext NetworkResult.Error("Token is null")

                val response = api.createNote(token, CreateRequest(title, content))
                Log.e("create", "${response.code()}?")
                Log.e("create", "${token}?")
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResult.Success(body)
                    }
                    else {
                        NetworkResult.Error("Response body is null")
                    }
                }
                else {
                    NetworkResult.Error("HTTP error: ${response.code()}")
                }
            }
            catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error while fetching note")
            }
        }
    }

    override suspend fun updateNote(id: String, title: String, content: String): NetworkResult<Note> {
        return withContext(dispatcher) {
            try {
                val token = tokenManager.getToken() ?:
                return@withContext NetworkResult.Error("Token is null")

                val response = api.updateNote(token, id, UpdateRequest(title, content))

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResult.Success(body)
                    }
                    else {
                        NetworkResult.Error("Response body is null")
                    }
                }
                else {
                    NetworkResult.Error("HTTP error: ${response.code()}")
                }
            }
            catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error while fetching note")
            }
        }
    }

    override suspend fun deleteNote(id: String): NetworkResult<Unit> {
        return withContext(dispatcher) {
            try {
                val token = tokenManager.getToken() ?:
                return@withContext NetworkResult.Error("Token is null")

                val response = api.deleteNote(token, id)

                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        NetworkResult.Success(body)
                    }
                    else {
                        NetworkResult.Error("Response body is null")
                    }
                }
                else {
                    NetworkResult.Error("HTTP error: ${response.code()}")
                }
            }
            catch (e: Exception) {
                NetworkResult.Error(e.message ?: "Unknown error while fetching note")
            }
        }
    }

}