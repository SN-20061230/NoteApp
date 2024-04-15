package com.example.noteapp2.navigation

sealed class Screens (val route: String) {
    object HomeView: Screens("home_screen")
    object AddView: Screens("add_screen/{id}")
    object EditView: Screens("edit_screen/{id}")
    object SplashScreen: Screens("splash_screen")

}