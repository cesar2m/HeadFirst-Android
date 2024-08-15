package com.cesar2m.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl

class TaskViewModelFactory (private val taskDao: TaskDao) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if(modelClass.isAssignableFrom(TaskViewModel::class.java)){
            return TaskViewModel(taskDao) as T
        }
        throw IllegalArgumentException("Unknow ViewModel")

    }


}