package com.cesar2m.guessinggame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MainActivityViewModelFactory(private val topicWordDao: TopicWordDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainActivityViewModel::class.java)){
            return MainActivityViewModel(topicWordDao) as T
        }
        throw IllegalArgumentException("Unknow ViewModel in MainActivityViewModelFactory")
    }
}