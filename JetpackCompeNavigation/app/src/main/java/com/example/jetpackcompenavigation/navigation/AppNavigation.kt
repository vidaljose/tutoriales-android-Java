package com.example.jetpackcompenavigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    
    NavHost(navController = navController, graph = AppScreens.FirstScreen.route.){
        composable()
    }
}