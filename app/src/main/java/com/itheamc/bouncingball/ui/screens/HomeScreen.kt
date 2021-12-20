package com.itheamc.bouncingball.ui.screens

import android.os.Build
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.itheamc.bouncingball.navigation.Routes
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.itheamc.bouncingball.BuildConfig
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun HomeScreen(
    navController: NavHostController
) {

    var rand by rememberSaveable {
        mutableStateOf(0)
    }
    val color by animateColorAsState(
        targetValue = when {
            rand % 5 == 0 -> Color.Red
            rand % 3 == 0 -> Color.Gray
            rand % 2 == 0 -> Color.Magenta
            else -> Color.Blue
        },
        animationSpec = tween(500)
    )
    
    LaunchedEffect(key1 = rand, block = {
        delay(500)
        val r = Random.nextInt(1, 200) + Random.nextInt(2, 50)
        rand = if (r == rand) r + 1 else r
    })

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.padding(top = 100.dp),
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = color,
                        fontSize = 60.sp,
                        fontWeight = FontWeight.ExtraBold,
                        fontFamily = FontFamily.Cursive,
                    ),
                    block = {
                        append("Bouncing")
                    }
                )
                append("\n\n")
                withStyle(
                    style = SpanStyle(
                        color = color,
                        fontSize = 42.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive
                    ),
                    block = {
                        append("Ball")
                    }
                )
                append("\n\n\n")
                withStyle(
                    style = SpanStyle(
                        color = color,
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive
                    ),
                    block = {
                        append("by")
                    }
                )
                append("\n")
                withStyle(
                    style = SpanStyle(
                        color = color,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Cursive
                    ),
                    block = {
                        append("itheamc")
                    }
                )

            },
            textAlign = TextAlign.Center
        )
        Button(
            modifier = Modifier.width(150.dp),
            onClick = {
                navController.navigate(Routes.GameScreen.name)
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = color,
                contentColor = MaterialTheme.colorScheme.onSurface
            )
        ) {
            Text(
                text = "Start",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            )
        }

        Text(
            text = BuildConfig.VERSION_NAME,
            style = MaterialTheme.typography.labelSmall.copy(
                color = color,
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.ExtraBold
            )
        )

    }
}