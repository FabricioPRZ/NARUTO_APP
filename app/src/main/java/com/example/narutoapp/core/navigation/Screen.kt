package com.example.narutoapp.core.navigation

sealed class Screen(val route: String) {
    object Naruto : Screen("naruto")
    object Search : Screen("search")
}