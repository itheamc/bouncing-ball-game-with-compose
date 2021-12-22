package com.itheamc.bouncingball.ui.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.itheamc.bouncingball.ui.navigation.GameNavHost
import com.itheamc.bouncingball.ui.navigation.Routes
import com.itheamc.bouncingball.viewmodel.GameViewModel

@Composable
fun Game(viewmodel: GameViewModel) {
    val navController = rememberNavController()
    GameNavHost(
        viewmodel = viewmodel,
        navController = navController,
        startDestination = Routes.HomeScreen.name
    )
}