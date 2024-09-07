package com.cesar2m.guessinggame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class GameViewModelFactory ( private final val dao: TopicWordDao, private val listTopicIds: ArrayList<Long>) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(GameViewModel::class.java))
            return GameViewModel(dao,listTopicIds) as T
        throw IllegalArgumentException("Unknown arg ViewModel")
    }
}