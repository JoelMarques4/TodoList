package com.breens.jetpackcomposeuiconcepts.taskmanager.feature.addedit

sealed interface AddEditEvent {
    data class TitleChanged(val title: String) : AddEditEvent
    data class BodyChanged(val body: String) : AddEditEvent
    data class StartTimeChanged(val startTime: String) : AddEditEvent
    data class EndTimeChanged(val endTime: String) : AddEditEvent
    data object Save: AddEditEvent

}