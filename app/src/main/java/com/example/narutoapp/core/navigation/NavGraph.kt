package com.example.narutoapp.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.narutoapp.features.naruto.presentation.screens.NarutoScreen
import com.example.narutoapp.features.search.presentation.screens.SearchScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Naruto.route
    ) {
        composable(Screen.Naruto.route) {
            NarutoScreen()
        }
        composable(Screen.Search.route) {
            SearchScreen()
        }
    }
}