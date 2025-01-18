package com.breens.jetpackcomposeuiconcepts.taskmanager.feature

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumedWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.breens.jetpackcomposeuiconcepts.ui.theme.TaskManagerAppJetpackComposeTheme

@Composable
fun AddEditScreen() {
    AddEditContent()
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddEditContent() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { },
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
        }){
        Column(
            modifier = Modifier
                .consumedWindowInsets(it)
                .padding(16.dp)
        ){
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(text = "Título")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )

            Spacer(modifier = Modifier.size(16.dp))

            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = {
                    Text(text = "Descrição (Opcional)")
                },
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun AddEditContentPreview() {
    TaskManagerAppJetpackComposeTheme {
        AddEditContent()
    }
}