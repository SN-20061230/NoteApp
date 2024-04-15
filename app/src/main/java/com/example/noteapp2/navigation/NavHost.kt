package com.example.noteapp2.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.example.noteapp2.database.AppDataBase
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.noteapp2.screens.SplashScreen
import com.example.noteapp2.screens.add.AddModel
import com.example.noteapp2.screens.add.AddView
import com.example.noteapp2.screens.add.AddViewModel
import com.example.noteapp2.screens.edit.EditModel
import com.example.noteapp2.screens.edit.EditView
import com.example.noteapp2.screens.edit.EditViewModel
import com.example.noteapp2.screens.home.HomeModel
import com.example.noteapp2.screens.home.HomeView
import com.example.noteapp2.screens.home.HomeViewModel

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    context: Context
) {
    val appDataBase = AppDataBase.getInstance(context)
    val appDao = appDataBase.getDao()

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = Screens.SplashScreen.route
    ) {

        val home = HomeModel(appDao)
        val home_m = HomeViewModel(navController, home)
        composable(Screens.HomeView.route) {
            HomeView(home_m, navController)
        }


        val edit = EditModel(appDao)
        composable(Screens.EditView.route, arguments = listOf(navArgument("id") {
            type = NavType.IntType
        })) {
            val id = it.arguments?.getInt("id")!!
            val avm = EditViewModel(navController, id, edit)
            EditView(avm, navController)
        }


        val add = AddModel(appDao)
        composable(Screens.AddView.route) {
            val avm1 = AddViewModel(navController, add)
            AddView(avm1, navController)
        }


        composable(Screens.SplashScreen.route) {
            SplashScreen(navController)
        }
    }
}