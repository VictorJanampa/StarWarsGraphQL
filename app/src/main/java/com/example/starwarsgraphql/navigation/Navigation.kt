package com.example.starwarsgraphql.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.starwarsgraphql.detailscreen.DetailScreen
import com.example.starwarsgraphql.peoplescreen.PeopleScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PeopleScreen.route){
        composable(route = Screen.PeopleScreen.route){
            PeopleScreen(navController = navController)
        }
        composable(
            route = "${Screen.DetailScreen.route}/{personId}",
            arguments = listOf(
                navArgument("personId"){
                    type = NavType.StringType
                }
            )
        ) {
            DetailScreen()
        }
    }
}