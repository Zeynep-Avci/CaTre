package com.example.catre.Navigation

sealed class AppScreens(val route: String) {
    object HomeScreen : AppScreens("home_screen")
    object LevelScreen : AppScreens("level_screen")
    object TaskScreen : AppScreens("task_screen")
    object BadgeScreen : AppScreens("badge_screen")
}