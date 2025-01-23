package com.breens.jetpackcomposeuiconcepts.taskmanager.feature.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breens.jetpackcomposeuiconcepts.navigation.AddEditRoute
import com.breens.jetpackcomposeuiconcepts.taskmanager.UiEvent
import com.breens.jetpackcomposeuiconcepts.taskmanager.components.ProfileHeaderComponent
import com.breens.jetpackcomposeuiconcepts.taskmanager.components.TaskComponent
import com.breens.jetpackcomposeuiconcepts.taskmanager.components.WelcomeMessageComponent
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.Task
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.TaskDatabaseProvider
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.TaskRepositoryImpl
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.taskList
import com.breens.jetpackcomposeuiconcepts.ui.theme.TaskManagerAppJetpackComposeTheme

@Composable
fun ListScreen(
    navigateToAddEditScreen: (id: Long?) -> Unit,
) {
    val context = LocalContext.current.applicationContext
    val database = TaskDatabaseProvider.provide(context)
    val repository = TaskRepositoryImpl(
        dao = database.taskDao
    )
    val viewModel = viewModel<ListViewModel>{
        ListViewModel(repository = repository)
    }

    val tasks by viewModel.tasks.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect { uiEvent ->
            when (uiEvent) {
                is UiEvent.Navigate<*> -> {
                    when(uiEvent.route){
                        is AddEditRoute -> {
                            navigateToAddEditScreen(uiEvent.route.id)
                        }
                    }
                }

                is UiEvent.NavigateBack -> {

                }

                is UiEvent.ShowSnackbar -> {

                }
            }

        }
    }

    ListContent(
        task = tasks,
        onEvent = viewModel::onEvent
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListContent(
    task: List<Task>,
    onEvent: (ListEvent) -> Unit,
) {
    var selectedScreen by remember { mutableStateOf(1) }
    val screens = listOf("Calendar", "Home", "Notifications")
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                onEvent(ListEvent.AddEdit(null)) },
                contentColor = Color.White,
                backgroundColor = Color.Black ) {
                Icon(
                    Icons.Filled.Add,
                    modifier = Modifier
                        .size(30.dp),
                    contentDescription = "Add",

                    )

            }
        },
        bottomBar = {
            BottomNavigation(
                modifier = Modifier.height(90.dp),
                backgroundColor = Color.White,
                elevation = 0.dp
            ) {
                screens.forEachIndexed { index, _ ->
                    val icon: ImageVector = when (index) {
                        0 -> Icons.Filled.CalendarMonth
                        1 -> Icons.Filled.Home
                        2 -> Icons.Filled.Mail
                        else -> Icons.Filled.Home
                    }
                    BottomNavigationItem(
                        selected = selectedScreen == index,
                        onClick = { selectedScreen = index },
                        icon = {
                            Box(
                                modifier = Modifier
                                    .size(50.dp)
                                    .clip(CircleShape)
                                    .background(if (selectedScreen == index) Color.Black else Color.White),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "Calendar",
                                    modifier = Modifier
                                        .size(50.dp)
                                        .padding(12.dp),
                                    tint = if (selectedScreen == index) Color.White else Color.Black
                                )
                            }
                        }
                    )
                }
            }
        }) {
        LazyColumn(
            modifier = Modifier.padding(it),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 16.dp,
                bottom = 16.dp
            )
        ) {
            item {
                ProfileHeaderComponent()
            }

            item {
                Spacer(modifier = Modifier.height(30.dp))

                WelcomeMessageComponent()

                Spacer(modifier = Modifier.height(30.dp))
            }

            //show all items on screen
            itemsIndexed(task) { index, taskIt ->
                TaskComponent(
                    task = taskIt,
                    onItemClick = {
                        onEvent(ListEvent.AddEdit(taskIt.id))
                    },
                    onDeleteClick = {
                        onEvent(ListEvent.Delete(taskIt.id))
                    },
                    /*
                    onCompletedChange = {
                        onEvent(ListEvent.CompleteTask(task.id, it))
                    },
                    */
                )
                if(index < task.lastIndex)
                    Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Preview
@Composable
private fun ListContentPreview() {
    TaskManagerAppJetpackComposeTheme(){
        ListContent(
            taskList,
            onEvent = {}
        )

    }
}