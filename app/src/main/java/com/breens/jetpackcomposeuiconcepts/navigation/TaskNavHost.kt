package com.breens.jetpackcomposeuiconcepts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.breens.jetpackcomposeuiconcepts.taskmanager.feature.addedit.AddEditScreen
import com.breens.jetpackcomposeuiconcepts.taskmanager.feature.list.ListScreen
import com.breens.jetpackcomposeuiconcepts.taskmanager.feature.login.LoginScreen
import kotlinx.serialization.Serializable

@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val id: Long? = null)

@Serializable
object LoginRoute

@Composable
fun TaskNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = LoginRoute){
        composable<LoginRoute>{
            LoginScreen(
                id = 0,
                navigateBack = {
                    navController.navigate(ListRoute)
                }
            )
        }
        composable<ListRoute> {
            ListScreen(
                navigateToAddEditScreen = { id ->
                    navController.navigate(AddEditRoute(id = id))
                }
            )
        }
        composable<AddEditRoute> { backStackEntry ->
            val addEditRoute = backStackEntry.toRoute<AddEditRoute>()
            AddEditScreen(
                id = addEditRoute.id,
                navigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}