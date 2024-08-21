package com.cesar2m.guessinggame

import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ChooseTopicViewModel(val topicWordDao: TopicWordDao)  :  ViewModel() {


    private var _listWordsToGame= MutableLiveData<ArrayList<TopicWorrdsEnum>>()
    val listWordsToGame: LiveData<ArrayList<TopicWorrdsEnum>> get() = _listWordsToGame

    val listTopics = topicWordDao.findAllTopic()
    init {

        addInitTopic()
        _listWordsToGame.value = ArrayList<TopicWorrdsEnum>()

    }

    fun addInitTopic(){
        viewModelScope.launch {
            val topic = Topic()
            topic.topicName = "Frutas y verduras"
            topic.activo = true

            topicWordDao.save(topic)
        }
    }
    fun addFruitToList(isAdd: Boolean){

        if(isAdd) {
            if (! (_listWordsToGame.value?.contains(TopicWorrdsEnum.FRUIT) as Boolean)) {
                _listWordsToGame.value?.add(TopicWorrdsEnum.FRUIT)
            }
        }else {
            if ((_listWordsToGame.value?.contains(TopicWorrdsEnum.FRUIT) as Boolean)) {
                _listWordsToGame.value?.remove(TopicWorrdsEnum.FRUIT)
            }
        }
    }

    fun addNamesToList(isAdd: Boolean){

        if(isAdd) {
            if (!(_listWordsToGame.value?.contains(TopicWorrdsEnum.FIRST_NAME) as Boolean)) {
                _listWordsToGame.value?.add(TopicWorrdsEnum.FIRST_NAME)
            }
        }else {
            if ((_listWordsToGame.value?.contains(TopicWorrdsEnum.FIRST_NAME) as Boolean) ) {
                _listWordsToGame.value?.remove(TopicWorrdsEnum.FIRST_NAME)
            }
        }
    }
    fun addFoodToList(isAdd: Boolean){

        if(isAdd) {
            if (!(_listWordsToGame.value?.contains(TopicWorrdsEnum.FOOD) as Boolean)) {
                _listWordsToGame.value?.add(TopicWorrdsEnum.FOOD)
            }
        }else {
            if ((_listWordsToGame.value?.contains(TopicWorrdsEnum.FOOD) as Boolean)) {
                _listWordsToGame.value?.remove(TopicWorrdsEnum.FOOD)
            }
        }
    }
    fun addSoToList(isAdd: Boolean){

        if(isAdd) {
            if (!(_listWordsToGame.value?.contains(TopicWorrdsEnum.SO) as Boolean)) {
                _listWordsToGame.value?.add(TopicWorrdsEnum.SO)
            }
        }else {
            if ((_listWordsToGame.value?.contains(TopicWorrdsEnum.SO) as Boolean)) {
                _listWordsToGame.value?.remove(TopicWorrdsEnum.SO)
            }
        }

    }


}