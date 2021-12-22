package com.itheamc.bouncingball.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.itheamc.bouncingball.ui.screens.GameScreen
import com.itheamc.bouncingball.ui.screens.HomeScreen
import com.itheamc.bouncingball.ui.screens.ScoresScreen
import com.itheamc.bouncingball.viewmodel.GameViewModel

@Composable
fun GameNavHost(
    viewmodel: GameViewModel,
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
                GameScreen(viewmodel = viewmodel, navController = navController)
            }

            composable(Routes.ScoresScreen.name) {
                ScoresScreen(viewmodel = viewmodel)
            }
        }
    )
}