package com.itheamc.bouncingball.roomdb

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ScoreDoa {

    @Query("SELECT * FROM scores")
    fun allScores(): Flow<List<Score>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(score: Score)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(score: Score)

    @Delete
    suspend fun delete(score: Score)

    @Delete
    suspend fun deleteScores(scores: List<Score>)

    @Query("DELETE FROM scores")
    suspend fun deleteAll()
}