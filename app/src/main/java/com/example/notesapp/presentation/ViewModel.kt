package com.example.notesapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.data.TokenManager
import com.example.notesapp.data.network.NetworkResult
import com.example.notesapp.data.repository.Repository
import com.example.notesapp.presentation.state.NoteListUIState
import com.example.notesapp.presentation.state.NoteUIState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ViewModel(
    private val repository: Repository,
    private val tokenManager: TokenManager
): ViewModel() {
    private val _noteListUIState =  MutableStateFlow(NoteListUIState())
    val noteListUIState: StateFlow<NoteListUIState> = _noteListUIState.asStateFlow()

    private val _noteUIState =  MutableStateFlow(NoteUIState())
    val noteUIState: StateFlow<NoteUIState> = _noteUIState.asStateFlow()

    fun updateNoteTitle(newText: String) {
        _noteUIState.update { currentState ->
            currentState.copy(title = newText)
        }
    }

    fun updateNoteText(newText: String) {
        _noteUIState.update { currentState ->
            currentState.copy(text = newText)
        }
    }

    fun loadNotes() {
        _noteListUIState.update {
            it.copy(isLoading = true)
        }
        viewModelScope.launch {
            when (val result = repository.getNotes()) {
                is NetworkResult.Success -> {
                    _noteListUIState.update {
                        it.copy(
                            isLoading = false,
                            notes = result.data.items,
                            count = result.data.count
                        )
                    }
                }
                is NetworkResult.Error -> {
                    _noteListUIState.update {
                        Log.e("ErrorNetwork", result.error)
                        it.copy(error = result.error)
                    }
                }
            }
        }
    }
    fun clearState() {
        viewModelScope.launch {
            _noteUIState.update {
                it.copy(
                    isLoading = false,
                    isNew = true,
                    id = "",
                    text = "",
                    title = "",
                    count = 0
                )
            }
        }

    }
    fun loadNote(id: String) {
        _noteUIState.update {
            it.copy(isLoading = true, isNew = false)
        }
        viewModelScope.launch {
            when (val result = repository.getNote(id)) {
                is NetworkResult.Success -> {
                    Log.e("loadNote", result.data.title)
                    Log.e("loadNote", result.data.content)
                    _noteUIState.update {
                        it.copy(
                            isLoading = false,
                            id = result.data.uuid,
                            text = result.data.content,
                            title = result.data.title
                        )
                    }
                }
                is NetworkResult.Error -> {
                    _noteListUIState.update {
                        Log.e("ErrorNetwork", result.error)
                        it.copy(error = result.error)
                    }
                }
            }
        }
    }

    fun register(login: String, password: String) {
        viewModelScope.launch{
            repository.register(login, password)
        }
    }

    fun login(login: String, password: String) {
        viewModelScope.launch {
            repository.login(login, password)
            loadNotes()
        }
        Log.e("вьюмодел", "работает")
    }

    fun createNote(title: String, content: String) {
        viewModelScope.launch {
            _noteUIState.update {
                it.copy(isNew = false)
            }
            repository.createNote(title, content)
            loadNotes()
            clearState()
        }
    }

    fun exit() {
        tokenManager.clearToken()
        clearState()
    }

    fun editNote() {
        viewModelScope.launch {
            repository.updateNote(
                _noteUIState.value.id,
                _noteUIState.value.title,
                _noteUIState.value.text
            )
            loadNotes()
            clearState()
        }
    }

}