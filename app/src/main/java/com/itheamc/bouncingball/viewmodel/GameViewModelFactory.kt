package com.itheamc.bouncingball.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itheamc.bouncingball.GameApplication
import java.lang.IllegalArgumentException

class GameViewModelFactory(private val application: GameApplication) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(application) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel")
        }
    }
}