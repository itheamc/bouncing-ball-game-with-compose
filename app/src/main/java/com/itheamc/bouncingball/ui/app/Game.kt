package com.itheamc.bouncingball.ui.app

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.itheamc.bouncingball.navigation.GameNavHost
import com.itheamc.bouncingball.navigation.Routes

@Composable
fun Game() {
    val navController = rememberNavController()
    GameNavHost(navController = navController, startDestination = Routes.HomeScreen.name)
}