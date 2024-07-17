package com.cesar2m.guessinggame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ResultViewModelFactory (private val finalResult: String, private val durationGame: Long) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ResultViewModel::class.java))
            return ResultViewModel(finalResult, durationGame) as T
        throw IllegalArgumentException("Unknown ViewModel")
    }
}