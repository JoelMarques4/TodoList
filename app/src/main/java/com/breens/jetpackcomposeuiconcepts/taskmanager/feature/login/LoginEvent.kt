package com.breens.jetpackcomposeuiconcepts.taskmanager.feature.login

sealed interface LoginEvent {
    data class NameChanged(val name: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    data object Save: LoginEvent

}