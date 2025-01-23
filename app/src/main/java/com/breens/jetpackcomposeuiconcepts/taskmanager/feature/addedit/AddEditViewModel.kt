package com.breens.jetpackcomposeuiconcepts.taskmanager.feature.addedit

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.breens.jetpackcomposeuiconcepts.taskmanager.UiEvent
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.TaskRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddEditViewModel(
    private val repository: TaskRepository,
) : ViewModel(){

    var title by mutableStateOf("")
    private set

    var body by mutableStateOf<String?>(null)
    private set

    var startTime by mutableStateOf("")
    private set

    var endTime by mutableStateOf("")
    private set

    private var _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddEditEvent){
        when(event){
            is AddEditEvent.TitleChanged -> {
                title = event.title
            }
            is AddEditEvent.BodyChanged -> {
                body = event.body
            }
            is AddEditEvent.StartTimeChanged -> {
                startTime = event.startTime
            }
            is AddEditEvent.EndTimeChanged -> {
                endTime = event.endTime
            }
            is AddEditEvent.Save -> {
                saveTask()
            }
        }
    }

    private fun saveTask() {
        viewModelScope.launch {

            if(title.isBlank()){
                _uiEvent.send(UiEvent.ShowSnackbar("O título não pode ser vazio"))
                return@launch
            }

            repository.insert(title, body, startTime, endTime)
            _uiEvent.send(UiEvent.NavigateBack)
        }
    }


}