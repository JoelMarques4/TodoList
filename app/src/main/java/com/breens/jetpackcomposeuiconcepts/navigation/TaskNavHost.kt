package com.breens.jetpackcomposeuiconcepts.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
object ListRoute

@Composable
fun TaskNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController , startDestination = )
}