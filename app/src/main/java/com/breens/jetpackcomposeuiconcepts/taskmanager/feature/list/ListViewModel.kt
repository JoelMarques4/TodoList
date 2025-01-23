package com.breens.jetpackcomposeuiconcepts.taskmanager.feature.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breens.jetpackcomposeuiconcepts.navigation.AddEditRoute
import com.breens.jetpackcomposeuiconcepts.taskmanager.UiEvent
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.TaskRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ListViewModel(
    private val repository: TaskRepository,
) : ViewModel() {

    val tasks = repository.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: ListEvent) {
        when (event) {
            is ListEvent.Delete -> {
                delete(event.id)

            }

            is ListEvent.CompleteChanged -> {
                completeChanged(event.id, event.isComplete)
            }

            is ListEvent.AddEdit -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(AddEditRoute(event.id)))
                }
            }
        }
    }

    private fun delete(id: Long){
        viewModelScope.launch {
            repository.delete(id)
        }
    }

    private fun completeChanged(id: Long, isCompleted: Boolean){
        viewModelScope.launch {
            repository.updateCompleted(id, isCompleted)
        }
    }
}