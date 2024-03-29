package com.itheamc.bouncingball.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Games
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Score
import androidx.compose.ui.graphics.vector.ImageVector

enum class Routes(
    val label: String,
    val icon: ImageVector
) {
    HomeScreen(
        label = "Home",
        icon = Icons.Filled.Home
    ),
    GameScreen(
        label = "Game",
        icon = Icons.Filled.Games
    ),
    ScoresScreen(
        label = "Scores",
        icon = Icons.Filled.Score
    )
}