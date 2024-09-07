package com.cesar2m.guessinggame

import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.math.log

class ChooseTopicViewModel(val topicWordDao: TopicWordDao)  :  ViewModel() {

    private val _listTopicsForPlay = MutableLiveData<ArrayList<Long>>()
    val listTopicsForPlay: LiveData<ArrayList<Long>>
        get() = _listTopicsForPlay


    val listTopics = topicWordDao.findAllTopic()
    init {

        _listTopicsForPlay.value = ArrayList<Long>()


    }

    fun addTopicForPlay(topicId: Long){
        if(_listTopicsForPlay.value?.contains(topicId) == true){
            _listTopicsForPlay.value?.remove(topicId)
        }else{
            _listTopicsForPlay.value?.add(topicId)
        }
    }



}