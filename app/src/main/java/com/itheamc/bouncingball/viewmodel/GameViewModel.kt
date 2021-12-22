package com.itheamc.bouncingball.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.itheamc.bouncingball.GameApplication
import com.itheamc.bouncingball.roomdb.Score
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class GameViewModel(application: GameApplication) : ViewModel() {

    private val repository = application.repository

    // All scores live data
    val allScores: LiveData<List<Score>> = repository.allScores.asLiveData()

    // Function to insert score
    fun insert(score: Score) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(score)
        }
    }

    // Function to update score
    fun update(score: Score) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(score)
        }
    }

    // Function to delete score
    fun delete(score: Score) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(score)
        }
    }

    // Function to delete all scores
    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAll()
        }
    }

}