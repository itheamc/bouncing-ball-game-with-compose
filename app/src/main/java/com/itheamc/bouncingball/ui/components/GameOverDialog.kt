package com.itheamc.bouncingball.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun GameOverDialog(
    visible: Boolean = false,
    onRestart: () -> Unit,
    onDismissRequest: () -> Unit,
    score: Int
) {
    var rand by rememberSaveable {
        mutableStateOf(0)
    }

    val color by animateColorAsState(
        targetValue = when {
            rand % 5 == 0 -> colors[0]
            rand % 3 == 0 -> colors[1]
            rand % 2 == 0 -> colors[2]
            else -> colors[3]
        },
        animationSpec = tween(500)
    )

    LaunchedEffect(key1 = rand, block = {
        delay(500)
        val r = Random.nextInt(1, 200) + Random.nextInt(2, 50)
        rand = if (r == rand) r + 1 else r
    })


    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(250)),
        exit = fadeOut(animationSpec = tween(250))
    ) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
                securePolicy = SecureFlagPolicy.SecureOn
            )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Game Over!!",
                    style = MaterialTheme.typography.displayMedium.copy(
                        color = color.copy(
                            alpha = 0.90f
                        ),
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily.Cursive
                    )
                )

                Spacer(modifier = Modifier.requiredSize(8.dp))

                Button(
                    modifier = Modifier
                        .height(36.dp)
                        .width(125.dp),
                    onClick = onRestart,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = color,
                        contentColor = Color.Black
                    )
                ) {
                    Text(
                        text = "Restart",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontFamily = FontFamily.Cursive,
                            color = Color.Black,
                            fontWeight = FontWeight.ExtraBold
                        )
                    )
                }

                Spacer(modifier = Modifier.requiredSize(6.dp))
                Text(
                    text = "Score:  $score",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = Color.White.copy(
                            alpha = 0.9f
                        ),
                    )
                )
            }
        }
    }

}


private val colors = listOf(
    Color.Green,
    Color.Cyan,
    Color.White,
    Color.Yellow
)