package com.cesar2m.guessinggame

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ChooseTopicViewModelFactory(private val topicWordDao: TopicWordDao): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(ChooseTopicViewModel::class.java)){
            return ChooseTopicViewModel(topicWordDao) as T
        }

        throw IllegalArgumentException("Unknow ViewModel in ChooseTopicViewModelFactory")

    }
}