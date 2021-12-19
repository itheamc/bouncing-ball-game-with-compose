package com.itheamc.bouncingball.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itheamc.bouncingball.ui.screens.GameScreen
import com.itheamc.bouncingball.ui.screens.HomeScreen

@Composable
fun GameNavHost(
    navController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        builder = {
            composable(Routes.HomeScreen.name) {
                HomeScreen(navController = navController)
            }

            composable(Routes.GameScreen.name) {
                GameScreen(navController = navController)
            }
        }
    )
}