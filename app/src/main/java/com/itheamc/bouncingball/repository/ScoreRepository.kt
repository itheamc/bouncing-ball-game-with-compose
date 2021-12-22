package com.itheamc.bouncingball.repository

import com.itheamc.bouncingball.roomdb.Score
import com.itheamc.bouncingball.roomdb.ScoreDoa

class ScoreRepository(private val scoreDoa: ScoreDoa) {

    // All Scores
    val allScores = scoreDoa.allScores()

    // Function to insert score
    suspend fun insert(score: Score) {
        scoreDoa.insert(score)
    }

    // Function to update score
    suspend fun update(score: Score) {
        scoreDoa.update(score)
    }

    // Function to delete score
    suspend fun delete(score: Score) {
        scoreDoa.delete(score)
    }

    // Function to delete more than one score
    suspend fun deleteScores(scores: List<Score>) {
        scoreDoa.deleteScores(scores)
    }

    // Function to delete all scores
    suspend fun deleteAll() {
        scoreDoa.deleteAll()
    }
}