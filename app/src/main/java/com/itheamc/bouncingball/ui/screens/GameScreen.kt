package com.itheamc.bouncingball.ui.screens

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Speed
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
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
import com.itheamc.bouncingball.navigation.Routes
import com.itheamc.bouncingball.ui.components.GameOverDialog
import com.itheamc.bouncingball.ui.components.SpeedControlDialog
import kotlin.random.Random

@Composable
fun GameScreen(
    navController: NavHostController
) {
    val config = LocalConfiguration.current
    val width = with(LocalDensity.current) {
        config.screenWidthDp.dp.roundToPx()
    }
    val height = with(LocalDensity.current) {
        config.screenHeightDp.dp.roundToPx()
    }

    var speedControlDialogVisibility by remember {
        mutableStateOf(false)
    }

    var gameOverDialogVisibility by remember {
        mutableStateOf(false)
    }

    val ballRadius = 40f
    val lineLength = 150f
    var speed by remember {
        mutableStateOf(2f)
    }

    var lineOffsetX by remember {
        mutableStateOf(100f)
    }

    var lineOffsetX1 by remember {
        mutableStateOf(lineOffsetX + 150f)
    }

    var lineOffsetY by remember {
        mutableStateOf(0f)
    }

    var xOffsetOfBall by remember {
        mutableStateOf(50f)
    }

    var yOffsetOfBall by remember {
        mutableStateOf(50f)
    }

    var stop by remember {
        mutableStateOf(false)
    }

    var collapsedStatus by remember {
        mutableStateOf(Ball.NotCollapsed)
    }

    var score by remember {
        mutableStateOf(0)
    }


    var xDirectionOfBall by remember {
        mutableStateOf(0)
    }

    var yDirectionBall by remember {
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
                score = 0
                xDirectionOfBall =
                    ((negativeDirection + positiveDirection).random() * speed).toInt()
                yDirectionBall = (positiveDirection.random() * speed).toInt()
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
            if ((yOffsetOfBall + ballRadius) in (lineOffsetY - (8 * speed))..(lineOffsetY + (8 * speed)) && xOffsetOfBall in (lineOffsetX - ballRadius / 2)..(lineOffsetX1 + ballRadius / 2)) {
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
                        lineOffsetX = offset.x - (lineLength / 2)
                        lineOffsetX1 = lineOffsetX + lineLength
                    },
                    onLongPress = {
                        lineOffsetX = it.x - (lineLength / 2)
                        lineOffsetX1 = lineOffsetX + lineLength
                    }
                )
            }
            .drawBehind {
                lineOffsetY = size.height - 200f
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

        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp),
            onClick = {
                speedControlDialogVisibility = !speedControlDialogVisibility
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Speed,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.5f
                )
            )
        }

        GameOverDialog(
            visible = gameOverDialogVisibility,
            onRestart = {
                // Restart the game
                gameOverDialogVisibility = false
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
            }
        )

        SpeedControlDialog(
            visible = speedControlDialogVisibility,
            speed = speed,
            onDismissRequest = {
                speedControlDialogVisibility = false
            },
            updateSpeed = {
                speed = it
            }
        )
    }
}