package com.example.notesapp.data

import android.content.SharedPreferences


class TokenManager(private val sharedPreferences: SharedPreferences) {
    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun setToken(token: String) {
        sharedPreferences.edit().putString("token", "Bearer $token").apply()
    }

    fun clearToken() {
        sharedPreferences.edit().remove("token").apply()
    }
}