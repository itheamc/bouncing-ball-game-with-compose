package com.itheamc.bouncingball.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.Slider
import androidx.compose.material.SliderDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import kotlin.math.roundToInt

@Composable
fun SpeedControlDialog(
    visible: Boolean = false,
    speed: Float,
    onDismissRequest: () -> Unit,
    updateSpeed: (Float) -> Unit
) {

    val color by animateColorAsState(
        targetValue = if (speed <= 2.5) Color.Green else if (speed in 2.5..3.75) Color.Yellow else Color.Red,
        animationSpec = tween(250)
    )

    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(250)),
        exit = fadeOut(animationSpec = tween(250))
    ) {
        Dialog(
            onDismissRequest = onDismissRequest,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                securePolicy = SecureFlagPolicy.SecureOn
            )
        ) {
            Column(
                modifier = Modifier.size(width = 250.dp, height = 125.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Speed",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = color
                    )
                )
                Text(
                    text = "${speed.roundToInt()}",
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = color
                    )
                )
                Slider(
                    modifier = Modifier.fillMaxWidth(0.9f),
                    valueRange = 1f..5f,
                    steps = 0,
                    value = speed,
                    onValueChange = updateSpeed,
                    colors = SliderDefaults.colors(
                        thumbColor = color,
                        activeTrackColor = color
                    )
                )
            }
        }
    }
}