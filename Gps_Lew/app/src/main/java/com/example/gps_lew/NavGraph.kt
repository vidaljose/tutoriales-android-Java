package com.example.gps_lew

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Nav(applicationContext: Context) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Login"){
        // Home
        composable(
            route = "Login"
        ){
            LoginScreen(navController)
        }

        // Detail
        composable(
            route = "Details/{name}",
            arguments = listOf(
                navArgument(name = "name"){
                    type = NavType.StringType
                }

            )
        ){backstackEntry ->
            DetailScreen(
                myName = backstackEntry.arguments?.getString("name"),
                applicationContext = applicationContext

            )
        }

    }
}