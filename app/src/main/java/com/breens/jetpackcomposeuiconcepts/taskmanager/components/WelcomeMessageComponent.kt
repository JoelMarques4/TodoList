package com.breens.jetpackcomposeuiconcepts.taskmanager.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breens.jetpackcomposeuiconcepts.R
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.TaskDatabaseProvider
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.TaskRepositoryImpl
import com.breens.jetpackcomposeuiconcepts.taskmanager.feature.list.ListViewModel
import com.breens.jetpackcomposeuiconcepts.ui.theme.LightGray

@Composable
@Preview
fun WelcomeMessageComponent() {
    val context = LocalContext.current.applicationContext
    val database = TaskDatabaseProvider.provide(context)
    val repository = TaskRepositoryImpl(
        dao = database.taskDao
    )
    val viewModel = viewModel<ListViewModel>{
        ListViewModel(repository = repository)
    }
    var taskAmount = repository.getTaskCount().collectAsState(initial = 0).value
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Oi Aluno!",
            fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
            fontSize = 22.sp
        )

        Text(
            text = "$taskAmount tarefas pendentes",
            fontFamily = FontFamily(Font(R.font.nunito_regular)),
            fontSize = 18.sp,
            color = LightGray
        )
    }
}