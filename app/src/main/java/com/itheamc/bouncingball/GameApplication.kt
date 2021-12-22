package com.itheamc.bouncingball

import android.app.Application
import com.itheamc.bouncingball.repository.ScoreRepository
import com.itheamc.bouncingball.roomdb.ScoreDatabase

class GameApplication : Application() {

    lateinit var repository: ScoreRepository

    override fun onCreate() {
        super.onCreate()
        val scoreDatabase: ScoreDatabase = ScoreDatabase
            .getInstance(this)
        repository = ScoreRepository(scoreDatabase.scoreDao())
    }
}