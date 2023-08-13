package com.example.catre.Navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.catre.Screens.BadgeScreen
import com.example.catre.Screens.HomeScreen
import com.example.catre.Screens.LevelScreen
import com.example.catre.Screens.TaskScreen
import com.example.catre.ViewModel.ToDoViewModel


@Composable
fun Navigation(viewModel: ToDoViewModel){
    val navController = rememberNavController()
    val viewModel: ToDoViewModel = viewModel()

    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController = navController, viewModel = viewModel)
        }

        composable(route = AppScreens.LevelScreen.route) {
            LevelScreen(navController = navController, viewModel = viewModel)
        }

        composable(route = AppScreens.TaskScreen.route) {
            TaskScreen(navController = navController, viewModel = viewModel)
        }
        composable(route = AppScreens.BadgeScreen.route) {
            BadgeScreen(navController = navController, viewModel = viewModel)
        }
    }
}