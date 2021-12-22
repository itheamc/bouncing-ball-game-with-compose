package com.itheamc.bouncingball.roomdb

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.itheamc.bouncingball.GameApplication


@Database(entities = [Score::class], version = 1, exportSchema = false)
abstract class ScoreDatabase : RoomDatabase() {

    // Abstract function to return the instance of the score dao
    abstract fun scoreDao(): ScoreDoa

    companion object {
        // Variable for the instance of the database
        @Volatile
        private var INSTANCE: ScoreDatabase? = null

        fun getInstance(application: GameApplication): ScoreDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    application.applicationContext,
                    ScoreDatabase::class.java,
                    "score_db"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}