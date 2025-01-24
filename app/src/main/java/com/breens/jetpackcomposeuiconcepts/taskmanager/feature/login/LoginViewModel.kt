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
) : ViewModel(){

    var name by mutableStateOf("")
    private set

    var password by mutableStateOf("")
    private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    init{
        id?.let {
            viewModelScope.launch {
                val user = repository.getBy(it)
                name = user?.name ?: ""
                password = user?.password ?: ""
                //body = task?.body
            }
        }
    }


    fun onEvent(event: LoginEvent){
        when(event){
            is LoginEvent.NameChanged -> {
                name = event.name
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

            if(name.isBlank()){
                _uiEvent.send(UiEvent.ShowSnackbar("O título não pode ser vazio"))
                return@launch
            }

            repository.insert(name, password, id)
            _uiEvent.send(UiEvent.NavigateBack)
        }
    }

}