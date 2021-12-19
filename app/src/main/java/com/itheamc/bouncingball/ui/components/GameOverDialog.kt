package com.itheamc.bouncingball.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy

@Composable
fun GameOverDialog(
    visible: Boolean = false,
    onRestart: () -> Unit,
    onDismissRequest: () -> Unit
) {
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
                        color = Color.Yellow.copy(
                            alpha = 0.90f
                        ),
                        fontWeight = FontWeight.Light,
                        fontFamily = FontFamily.Cursive
                    )
                )

                Spacer(modifier = Modifier.requiredSize(8.dp))

                OutlinedButton(
                    modifier = Modifier.height(36.dp),
                    onClick = onRestart,
                    border = BorderStroke(
                        width = 1.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.Blue,
                                Color.Magenta,
                                Color.Red,
                                Color.Yellow
                            )
                        )
                    ),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = contentColorFor(backgroundColor = MaterialTheme.colorScheme.primaryContainer)
                    )
                ) {
                    Text(
                        text = "Restart",
                        style = MaterialTheme.typography.labelLarge.copy(
                            fontFamily = FontFamily.Cursive
                        )
                    )
                }
            }
        }
    }

}