package com.example.notesapp.di
import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.notesapp.data.TokenManager
import com.example.notesapp.data.network.api.API
import com.example.notesapp.data.repository.Repository
import com.example.notesapp.data.repository.RepositoryImpl
import com.example.notesapp.presentation.ViewModel
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private val json = Json { ignoreUnknownKeys = true }

val appModules = module {
    single {
        androidContext().getSharedPreferences(
            "my_prefs",
            Context.MODE_PRIVATE
        )
    }
    single { TokenManager(get()) }
    single<Repository> { RepositoryImpl(get(), get(), get()) }
    single { Dispatchers.IO }
    viewModel { ViewModel(get(), get()) }
    single {
        Retrofit.Builder()
            .addConverterFactory(
                json.asConverterFactory(contentType = "application/json".toMediaType())
            )
            .baseUrl("http://85.193.85.61:8080/")
            .build()
    }
    single { get<Retrofit>().create(API::class.java) }
}