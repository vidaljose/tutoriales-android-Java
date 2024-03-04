package com.example.navigationarguments

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Nav(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "Home"){
        // Home
        composable(
            route = "Home"
        ){
            HomeScreen(navController)
        }

        // Detail
        composable(
            route = "Details/{name}/{age}",
            arguments = listOf(
                navArgument(name = "name"){
                    type = NavType.StringType
                },
                navArgument(name = "age"){
                    type = NavType.IntType
                }
            )
        ){backstackEntry ->
            DetailScreen(
                myName = backstackEntry.arguments?.getString("name"),
                myAge = backstackEntry.arguments?.getInt("age")
            )
        }

    }
}