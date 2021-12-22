package com.itheamc.bouncingball.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.itheamc.bouncingball.roomdb.Score
import com.itheamc.bouncingball.ui.components.ScoreView
import com.itheamc.bouncingball.viewmodel.GameViewModel

@Composable
fun ScoresScreen(viewmodel: GameViewModel) {

    val scores: List<Score> by viewmodel.allScores.observeAsState(listOf())

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(scores) { score ->
            ScoreView(score = score)
        }
    }

}