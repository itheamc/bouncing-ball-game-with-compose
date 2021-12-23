package com.itheamc.bouncingball.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
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
fun SettingDialog(
    visible: Boolean = false,
    speed: Float,
    ballRadius: Float,
    lineHeight: Float,
    onDismissRequest: () -> Unit,
    updateSpeed: (Float) -> Unit,
    updateRadius: (Float) -> Unit,
    updateLineHeight: (Float) -> Unit
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
                modifier = Modifier.fillMaxWidth(0.8f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                /**
                 * ------------------------------------------------------------
                 * For Speed Control
                 */
                CustomSlider(
                    color = color,
                    value = speed,
                    onValueChange = updateSpeed,
                    label = "Speed",
                    range = 1f..5f
                )

                /**
                 * ------------------------------------------------------------
                 * For Radius Control
                 */
                CustomSlider(
                    color = Color.White.copy(
                        alpha = 0.75f
                    ),
                    value = ballRadius,
                    onValueChange = updateRadius,
                    label = "Ball Radius",
                    range = 30f..120f
                )

                /**
                 * ------------------------------------------------------------
                 * For Line Height Control
                 */
                CustomSlider(
                    color = Color.White.copy(
                        alpha = 0.75f
                    ),
                    value = lineHeight,
                    onValueChange = updateLineHeight,
                    label = "Line Height",
                    range = 100f..300f
                )
            }

        }
    }

}


@Composable
fun CustomSlider(
    color: Color,
    value: Float,
    onValueChange: (Float) -> Unit,
    label: String,
    range: ClosedFloatingPointRange<Float>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall.copy(
                color = color
            )
        )
        Text(
            text = "${value.roundToInt()}",
            style = MaterialTheme.typography.titleLarge.copy(
                color = color
            )
        )
        Slider(
            modifier = Modifier.fillMaxWidth(0.9f),
            valueRange = range,
            steps = 0,
            value = value,
            onValueChange = onValueChange,
            colors = SliderDefaults.colors(
                thumbColor = color,
                activeTrackColor = color
            )
        )

        Spacer(modifier = Modifier.requiredSize(16.dp))
    }
}