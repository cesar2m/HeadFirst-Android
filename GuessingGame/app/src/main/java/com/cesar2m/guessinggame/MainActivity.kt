package com.cesar2m.guessinggame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel
    lateinit var mainActivityViewModelFactory: MainActivityViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topicWordDao = GuessingGameDataBase.getInstance(application).topicWordDao
        mainActivityViewModelFactory  = MainActivityViewModelFactory(topicWordDao)

        mainActivityViewModel = ViewModelProvider(this, mainActivityViewModelFactory)
            .get(MainActivityViewModel::class.java)

        mainActivityViewModel.listAllTopics.observe(this, Observer { listT ->

            
            if( null != listT && !listT.isEmpty()){
                mainActivityViewModel.listAllTopics.observe(this, Observer{ listW ->
                    if(null == listW || listW.isEmpty()) {
                        mainActivityViewModel.addInitWords(listT)
                    }
                })

            }else{
                var listAllTopics = mainActivityViewModel.addInitTopic()
                mainActivityViewModel.addInitWords(listAllTopics)


            }
        })




    }




}