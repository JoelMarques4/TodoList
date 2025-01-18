package com.breens.jetpackcomposeuiconcepts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.breens.jetpackcomposeuiconcepts.taskmanager.components.ListScreen
import com.breens.jetpackcomposeuiconcepts.taskmanager.feature.AddEditScreen
import kotlinx.serialization.Serializable

@Serializable
object ListRoute

@Serializable
data class AddEditRoute(val Id: Long? = null)

@Composable
fun TaskNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = ListRoute) {
        composable<ListRoute>{
            ListScreen()
        }
        composable<AddEditRoute> { backStackEntry ->
            var addEditRoute = backStackEntry.toRoute<AddEditRoute>()
            AddEditScreen()
        }
    }
}