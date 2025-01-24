package com.breens.jetpackcomposeuiconcepts.taskmanager.feature.login

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.breens.jetpackcomposeuiconcepts.R
import com.breens.jetpackcomposeuiconcepts.taskmanager.UiEvent
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.userdata.UserDataDatabaseProvider
import com.breens.jetpackcomposeuiconcepts.taskmanager.data.userdata.UserDataRepositoryImpl
import com.breens.jetpackcomposeuiconcepts.ui.theme.TaskManagerAppJetpackComposeTheme

@Composable
fun LoginScreen(
    id: Long?,
    navigateBack: () -> Unit,
) {

    val context = LocalContext.current.applicationContext
    val database = UserDataDatabaseProvider.provide(context)
    val repository = UserDataRepositoryImpl(
        dao = database.userDao
    )
    val viewModel = viewModel<LoginViewModel>{
        LoginViewModel(
            id = id,
            repository = repository
        )
    }

    val name = viewModel.name
    val password = viewModel.password


    LaunchedEffect(Unit) {
        viewModel.uiEvent.collect{ uiEvent ->
            when(uiEvent){
                is UiEvent.ShowSnackbar -> {
                }
                UiEvent.NavigateBack ->{
                    navigateBack()

                }

                is UiEvent.Navigate<*> ->{
                }
            }
        }
    }


    LoginContent(
        name = name,
        password = "password",
        onEvent = viewModel::onEvent,
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LoginContent(
    name: String,
    password: String,
    onEvent: (LoginEvent) -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onEvent(LoginEvent.Save)
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
        }

    ){ paddingValues ->
        Column(
            modifier = Modifier
                .consumeWindowInsets(paddingValues)
                .padding(16.dp)
        ){
            Text(
                text = "Login",
                fontFamily = FontFamily(Font(R.font.nunito_extrabold)),
                fontSize = 22.sp
            )

            Spacer(modifier = Modifier.size(56.dp))


            OutlinedTextField(
                value = name,
                onValueChange = {
                    onEvent(
                        LoginEvent.NameChanged(it)
                    )
                },
                placeholder = {
                    Text(text = "Nome:")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFF1F4FF))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(14.dp)
                    )

            )
            OutlinedTextField(
                value = password,
                onValueChange = {
                    onEvent(
                        LoginEvent.NameChanged(it)
                    )
                },
                placeholder = {
                    Text(text = "Senha:")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFFF1F4FF))
                    .border(
                        width = 1.dp,
                        color = Color(0xFFFFFFFF),
                        shape = RoundedCornerShape(14.dp)
                    )

            )
        }
    }
}

@Preview
@Composable
private fun LoginContentPreview() {
    TaskManagerAppJetpackComposeTheme {
        LoginContent(
            name = "Nome:",
            password = "Description",
            onEvent = {},
        )
    }
}