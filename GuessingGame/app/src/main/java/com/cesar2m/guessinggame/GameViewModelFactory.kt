package com.cesar2m.guessinggame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameViewModelFactory ( private final val listWordsToGame: Array<String>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java))
            return GameViewModel(listWordsToGame) as T
        throw IllegalArgumentException("Unknown arg ViewModel")
    }
}