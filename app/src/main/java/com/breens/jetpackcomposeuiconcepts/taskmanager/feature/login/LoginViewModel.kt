package com.breens.jetpackcomposeuiconcepts.taskmanager.feature.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breens.jetpackcomposeuiconcepts.taskmanager.UiEvent
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.userdata.UserDataRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class LoginViewModel(
    private val id: Long? = null,
    private val repository: UserDataRepository,
) : ViewModel() {

    var username by mutableStateOf("")
        private set

    var password by mutableStateOf("")
        private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init {
        id?.let {
            viewModelScope.launch {
                try {
                    val user = repository.getBy(it)
                    username = user?.name ?: ""
                    password = user?.name ?: ""
                } catch (e: Exception) {
                    _uiEvent.send(UiEvent.ShowSnackbar("Failed to load user data"))
                }
            }
        }
    }


    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.NameChanged -> {
                username = event.name
            }

            is LoginEvent.PasswordChanged -> {
                password = event.password
            }

            is LoginEvent.Save -> {
                saveTask()
            }
        }
    }

    private fun saveTask() {
        viewModelScope.launch {

            if (username.isBlank()) {
                _uiEvent.send(UiEvent.ShowSnackbar("O título não pode ser vazio"))
                return@launch
            }

            repository.insert(username, password, id)
            _uiEvent.send(UiEvent.NavigateBack)
        }
    }
}