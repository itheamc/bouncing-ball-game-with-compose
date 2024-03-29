package com.itheamc.bouncingball.ui.screens

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Grain
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.itheamc.bouncingball.Ball
import com.itheamc.bouncingball.roomdb.Score
import com.itheamc.bouncingball.ui.components.GameOverDialog
import com.itheamc.bouncingball.ui.components.SettingDialog
import com.itheamc.bouncingball.ui.navigation.Routes
import com.itheamc.bouncingball.viewmodel.GameViewModel
import kotlin.random.Random

@Composable
fun GameScreen(
    viewmodel: GameViewModel,
    navController: NavHostController
) {
    val config = LocalConfiguration.current
    val width = with(LocalDensity.current) {
        config.screenWidthDp.dp.roundToPx()
    }
    val height = with(LocalDensity.current) {
        config.screenHeightDp.dp.roundToPx()
    }

    var settingDialogVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    var gameOverDialogVisibility by rememberSaveable {
        mutableStateOf(false)
    }

    var lineHeight by rememberSaveable {
        mutableStateOf(150f)
    }

    var ballRadius by rememberSaveable {
        mutableStateOf(40f)
    }

    var lineLength by rememberSaveable {
        mutableStateOf(150f)
    }
    var speed by rememberSaveable {
        mutableStateOf(2f)
    }

    var lineOffsetX by rememberSaveable {
        mutableStateOf(100f)
    }

    var lineOffsetX1 by rememberSaveable {
        mutableStateOf(lineOffsetX + lineLength)
    }

    var lineOffsetY by rememberSaveable {
        mutableStateOf(0f)
    }

    var xOffsetOfBall by rememberSaveable {
        mutableStateOf(50f)
    }

    var yOffsetOfBall by rememberSaveable {
        mutableStateOf(50f)
    }

    var stop by rememberSaveable {
        mutableStateOf(false)
    }

    var collapsedStatus by rememberSaveable {
        mutableStateOf(Ball.NotCollapsed)
    }

    var score by rememberSaveable {
        mutableStateOf(0)
    }


    var xDirectionOfBall by rememberSaveable {
        mutableStateOf(0)
    }

    var yDirectionBall by rememberSaveable {
        mutableStateOf(0)
    }

    val negativeDirection = listOf(-8, -10)
    val positiveDirection = listOf(8, 10, 8, 10, 10)

    LaunchedEffect(collapsedStatus) {
        when (collapsedStatus) {
            Ball.TopCollapsed -> {
                xDirectionOfBall =
                    ((negativeDirection + positiveDirection).random() * speed).toInt()
                yDirectionBall = (positiveDirection.random() * speed).toInt()
            }
            Ball.LineCollapsed -> {
                score += 1
                xDirectionOfBall =
                    ((negativeDirection + positiveDirection).random() * speed).toInt()
                yDirectionBall = (negativeDirection.random() * speed).toInt()
            }
            Ball.LeftCollapsed -> {
                xDirectionOfBall = (positiveDirection.random() * speed).toInt()
                yDirectionBall = ((negativeDirection + positiveDirection).random() * speed).toInt()
            }
            Ball.RightCollapsed -> {
                xDirectionOfBall = (negativeDirection.random() * speed).toInt()
                yDirectionBall = ((negativeDirection + positiveDirection).random() * speed).toInt()
            }
            Ball.BottomCollapsed -> {
                xDirectionOfBall =
                    ((negativeDirection + positiveDirection).random() * speed).toInt()
                yDirectionBall = (positiveDirection.random() * speed).toInt()
                viewmodel.insert(Score(_score = score))
                gameOverDialogVisibility = true
                stop = true
            }
            else -> {
                xDirectionOfBall =
                    ((negativeDirection + positiveDirection).random() * speed).toInt()
                yDirectionBall = ((negativeDirection + positiveDirection).random() * speed).toInt()
            }
        }
    }

    LaunchedEffect(keys = arrayOf(xOffsetOfBall, stop)) {
        if (!stop) {
            if ((yOffsetOfBall + ballRadius) in (lineOffsetY - (10 * speed))..(lineOffsetY + (10 * speed)) && xOffsetOfBall in (lineOffsetX - ballRadius)..(lineOffsetX1 + ballRadius)) {
                collapsedStatus = Ball.LineCollapsed
            } else if (yOffsetOfBall - ballRadius <= 0) {
                collapsedStatus = Ball.TopCollapsed
            } else if (yOffsetOfBall + ballRadius >= height || (xOffsetOfBall + ballRadius >= width && yOffsetOfBall > lineOffsetY) || (xOffsetOfBall - ballRadius <= 0 && yOffsetOfBall > lineOffsetY)) {
                collapsedStatus = Ball.BottomCollapsed
                xOffsetOfBall = Random.nextInt(0, width).toFloat()
                yOffsetOfBall = 0f
            } else if (xOffsetOfBall + ballRadius >= width) {
                collapsedStatus = Ball.RightCollapsed
            } else if (xOffsetOfBall - ballRadius <= 0) {
                collapsedStatus = Ball.LeftCollapsed
            }
            xOffsetOfBall += xDirectionOfBall
            yOffsetOfBall += yDirectionBall
        }
    }

    // Update LineOffsetY whenever lineHeight is change
    LaunchedEffect(key1 = lineHeight, block = {
        lineOffsetY = height - lineHeight
    })

    // Adjust lineOffset1 as per ball radius
    LaunchedEffect(key1 = ballRadius, block = {
        lineOffsetX1 = lineOffsetX + lineLength
    })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {

                    },
                    onDoubleTap = {

                    },
                    onPress = { offset ->
                        if (!stop) {
                            lineOffsetX = offset.x - (lineLength / 2)
                            lineOffsetX1 = lineOffsetX + lineLength
                        }
                    },
                    onLongPress = {
                        if (!stop) {
                            lineOffsetX = it.x - (lineLength / 2)
                            lineOffsetX1 = lineOffsetX + lineLength
                        }
                    }
                )
            }
            .draggable(
                enabled = true,
                state = rememberDraggableState(
                    onDelta = {
                        if (!stop) {
                            lineOffsetX += it
                            lineOffsetX1 += it
                        }
                    }
                ),
                orientation = Orientation.Horizontal
            )
            .drawBehind {
//                lineOffsetY = size.height - 150f
                drawLine(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Yellow,
                            Color.Green,
                            Color.Magenta,
                            Color.Red,
                            Color.Cyan,
                            Color.Blue
                        ),
                        tileMode = TileMode.Mirror
                    ),
                    start = Offset(lineOffsetX, lineOffsetY),
                    end = Offset(lineOffsetX1, lineOffsetY),
                    strokeWidth = 30f,
                    cap = StrokeCap.Round
                )

                drawCircle(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.Yellow,
                            Color.Green,
                            Color.Magenta,
                            Color.Red,
                            Color.Cyan,
                            Color.Blue
                        ),
                        tileMode = TileMode.Clamp
                    ),
                    center = Offset(xOffsetOfBall, yOffsetOfBall),
                    radius = ballRadius
                )
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "$score", style = MaterialTheme.typography.displayLarge.copy(
                fontWeight = FontWeight.ExtraBold,
                fontSize = 150.sp,
                color = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.05f
                )
            )
        )


        Row(
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.TopEnd),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(2.dp)
        ) {

            IconButton(
                onClick = {
                    navController.navigate(Routes.ScoresScreen.name)
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Grain,
                    contentDescription = "scores",
                    tint = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5f
                    )
                )
            }

            IconButton(
                onClick = {
                    stop = !stop
                }
            ) {
                Icon(
                    imageVector = if (stop) Icons.Filled.PlayArrow else Icons.Filled.Pause,
                    contentDescription = "playpause",
                    tint = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5f
                    )
                )
            }

            IconButton(
                onClick = {
                    settingDialogVisibility = !settingDialogVisibility
                    stop = true
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurface.copy(
                        alpha = 0.5f
                    )
                )
            }
        }

        GameOverDialog(
            viewmodel = viewmodel,
            visible = gameOverDialogVisibility,
            onRestart = {
                // Restart the game
                gameOverDialogVisibility = false
                score = 0
                stop = false
            },
            onDismissRequest = {
                // show home
                gameOverDialogVisibility = false
                navController.navigate(Routes.HomeScreen.name) {
                    launchSingleTop = true
                    popUpTo(Routes.HomeScreen.name) {
                        inclusive = true
                    }
                }
            },
            score = score
        )

        SettingDialog(
            visible = settingDialogVisibility,
            speed = speed,
            ballRadius = ballRadius,
            lineHeight = lineHeight,
            onDismissRequest = {
                settingDialogVisibility = false
                stop = false
            },
            updateSpeed = {
                speed = it
            },
            updateRadius = {
                ballRadius = it
                lineLength = 150f + ((it - 40f) / 2)
            },
            updateLineHeight = {
                lineHeight = it
            }
        )
    }
}