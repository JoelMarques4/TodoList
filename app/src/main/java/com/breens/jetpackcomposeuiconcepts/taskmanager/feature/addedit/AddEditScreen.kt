package com.breens.jetpackcomposeuiconcepts.taskmanager.feature.addedit

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breens.jetpackcomposeuiconcepts.taskmanager.UiEvent
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.TaskDatabaseProvider
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.TaskRepositoryImpl
import com.breens.jetpackcomposeuiconcepts.ui.theme.TaskManagerAppJetpackComposeTheme

@Composable
fun AddEditScreen(
    id: Long?,
    navigateBack: () -> Unit,
) {
    val context = LocalContext.current.applicationContext
    val database = TaskDatabaseProvider.provide(context)
    val repository = TaskRepositoryImpl(
        dao = database.taskDao
    )
    val viewModel = viewModel<AddEditViewModel>{
        AddEditViewModel(
            id = id,
            repository = repository
        )
    }

    val title = viewModel.title
    val description = viewModel.body
    val startTime = viewModel.startTime
    val endTime = viewModel.endTime

    val snackbarHostState = remember{
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect{ uiEvent ->
            when(uiEvent){
                is UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                    )
                }
                UiEvent.NavigateBack ->{
                    navigateBack()

                }

                is UiEvent.Navigate<*> ->{
                }
            }
        }
    }


    AddEditContent(
        title = title,
        description = description,
        startTime = startTime,
        endTime = endTime,
        onEvent = viewModel::onEvent,
        snackbarHostState = snackbarHostState,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddEditContent(
    title: String,
    description: String?,
    startTime: String,
    endTime: String,
    snackbarHostState: SnackbarHostState,
    onEvent: (AddEditEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(AddEditEvent.Save)
                },
                contentColor = Color.White,
                backgroundColor = Color.Black
            ) {
                Icon(
                    Icons.Filled.Check,
                    modifier = Modifier
                        .size(30.dp),
                    contentDescription = "Save",
                    )

            }
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(paddingValues)
                .padding(16.dp)
        ){
            OutlinedTextField(
                value = title,
                onValueChange = {
                    onEvent(
                        AddEditEvent.TitleChanged(it)
                    )
                },
                placeholder = {
                    Text(text = "Título")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(16.dp))

            OutlinedTextField(
                value = description ?: "",
                onValueChange = {
                    onEvent(
                        AddEditEvent.BodyChanged(it)
                    )
                },
                placeholder = {
                    Text(text = "Descrição (Opcional)")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.size(16.dp))
            OutlinedTextField(
                value = startTime,
                onValueChange = {
                    onEvent(
                        AddEditEvent.StartTimeChanged(it)
                    )
                },
                placeholder = {
                    Text(text = "Horário Inicial")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(16.dp))

            OutlinedTextField(
                value = endTime,
                onValueChange = {
                    onEvent(
                        AddEditEvent.EndTimeChanged(it)
                    )
                },
                placeholder = {
                    Text(text = "Horário Final")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(16.dp))
        }
    }
}

@Preview
@Composable
private fun AddEditContentPreview() {
    TaskManagerAppJetpackComposeTheme {
        AddEditContent(
            title = "Title",
            description = "Description",
            startTime = "Start Time",
            endTime = "End Time",
            onEvent = {},
            snackbarHostState = SnackbarHostState()
        )
    }
}