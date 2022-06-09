package com.example.starwarsgraphql.navigation

sealed class Screen(val route: String) {
    object PeopleScreen : Screen("people_screen")
    object DetailScreen : Screen("detail_screen")

    fun <T: Any> withArgs(vararg args: T): String{
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}