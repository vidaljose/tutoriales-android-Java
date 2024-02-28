package com.example.jetpackcompenavigation.navigation

sealed class AppScreens(val route: String) {
    object FirstScreen: AppScreens("First_screen")
    object SecondScreen: AppScreens("Second_screen")

}