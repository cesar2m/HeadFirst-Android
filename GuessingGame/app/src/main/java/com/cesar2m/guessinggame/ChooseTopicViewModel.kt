package com.cesar2m.guessinggame

import android.widget.CheckBox
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChooseTopicViewModel  :  ViewModel() {


    private var _listWordsToGame= MutableLiveData<ArrayList<TopicWorrdsEnum>>()
    val listWordsToGame: LiveData<ArrayList<TopicWorrdsEnum>> get() = _listWordsToGame

    init {

        _listWordsToGame.value = ArrayList<TopicWorrdsEnum>()
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