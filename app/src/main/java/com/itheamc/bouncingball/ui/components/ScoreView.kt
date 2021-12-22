package com.itheamc.bouncingball.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.itheamc.bouncingball.roomdb.Score
import com.itheamc.bouncingball.utils.toDate
import com.itheamc.bouncingball.utils.toTime
import kotlin.random.Random

@Composable
fun ScoreView(score: Score) {
    Box(
        modifier = Modifier.fillMaxWidth(0.9f),
        contentAlignment = if (score._score % 5 == 0) Alignment.Center else if (score._score % 3 == 0) Alignment.CenterEnd else Alignment.CenterStart
    ) {
        Column(
            modifier = Modifier
                .size((100 + score._score).dp)
                .clip(
                    shape = CircleShape
                )
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(
                                alpha = 50,
                                red = Random.nextInt(0, 256),
                                green = Random.nextInt(0, 256),
                                blue = Random.nextInt(0, 256)
                            ),
                            Color(
                                alpha = 50,
                                red = Random.nextInt(0, 256),
                                green = Random.nextInt(0, 256),
                                blue = Random.nextInt(0, 256)
                            ),
                            Color(
                                alpha = 50,
                                red = Random.nextInt(0, 256),
                                green = Random.nextInt(0, 256),
                                blue = Random.nextInt(0, 256)
                            )
                        )
                    )
                )
                .clickable(
                    onClick = {}
                ),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Score",
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                )
            )
            Text(
                text = score._score.toString(),
                style = MaterialTheme.typography.displayMedium.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = (24 + (score._score/1.5)).sp
                )
            )
            Divider()
            Text(
                text = score._date.toDate(),
                style = MaterialTheme.typography.labelLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            Text(
                text = score._date.toTime(),
                style = MaterialTheme.typography.labelSmall.copy(
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 8.sp
                )
            )

        }
    }
}