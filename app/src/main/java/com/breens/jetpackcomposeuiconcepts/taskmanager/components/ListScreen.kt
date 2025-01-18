package com.breens.jetpackcomposeuiconcepts.taskmanager.components

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.Task
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.taskList
import com.breens.jetpackcomposeuiconcepts.ui.theme.TaskManagerAppJetpackComposeTheme

@Composable
fun ListScreen() {
    ListContent(task = emptyList())
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ListContent(
    task: List<Task>
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .consumedWindowInsets(paddingValues)
        ) {
            itemsIndexed(task) { index, taskIt ->
                TaskComponent(
                    task = taskIt,
                    onItemClick = { },
                    onDeleteClick = { }
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
            taskList
        )
    }
}