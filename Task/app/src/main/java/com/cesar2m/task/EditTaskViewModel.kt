package com.cesar2m.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditTaskViewModel(taskId: Long,val taskDao: TaskDao) : ViewModel() {

    val task =  taskDao.get(taskId)
    private val _navigateToList =MutableLiveData<Boolean> (false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    fun updateTask(){
        viewModelScope.launch {
            taskDao.update(task.value!!)
            _navigateToList.value = true
        }
    }

    fun deleteTask(){
        viewModelScope.launch {
            taskDao.delete(task.value!!)//Si task es NULL laza un NullPointerException
            _navigateToList.value = true
        }
    }


    fun onNavigateToList(){
        _navigateToList.value = false
    }

}
