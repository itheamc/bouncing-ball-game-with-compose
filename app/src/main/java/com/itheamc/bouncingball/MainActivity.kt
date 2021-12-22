package com.itheamc.bouncingball

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import com.itheamc.bouncingball.ui.app.Game
import com.itheamc.bouncingball.ui.theme.BouncingBallTheme
import com.itheamc.bouncingball.viewmodel.GameViewModel
import com.itheamc.bouncingball.viewmodel.GameViewModelFactory

class MainActivity : ComponentActivity() {

    private val viewmodel: GameViewModel by viewModels { GameViewModelFactory(application as GameApplication) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BouncingBallTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Game(viewmodel = viewmodel)
                }
            }
        }
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}


enum class Ball {
    TopCollapsed,
    LeftCollapsed,
    RightCollapsed,
    BottomCollapsed,
    LineCollapsed,
    NotCollapsed
}