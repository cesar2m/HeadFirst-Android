package com.cesar2m.task


import androidx.arch.core.util.Function
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch



class TaskViewModel(val dao: TaskDao) : ViewModel() {

    var newTaskName = ""



    private var _allTasks = MutableLiveData<List<Task>>()
    val allTasks : LiveData<List<Task>> get() = _allTasks




    fun addTask(){
        viewModelScope.launch {

            val task = Task()
            task.taskName = newTaskName
            var listTareasFOund: List<Task> = if(null == dao.getAll().value)  emptyList() else  dao.getAll().value as List<Task>
            dao.insert(task)
        }
        _allTasks.value = dao.getAll().value

    }

    fun formatTasks(listTasks: List<Task>): String {
        return listTasks.fold(""){
            str, item -> str + '\n' + formatTask(item)
        }
    }

     fun formatTask(itemTask: Task): String {
         var str = "ID: ${itemTask.taskId}"
         str += '\n' + "Name: ${itemTask.taskName}"
         str += '\n' + "Complete: ${itemTask.taskDone}" + '\n'
         return str;
     }

    fun formatAllTasks() : String{

        var listAllTasks: List<Task> = emptyList<Task>()
        if(null != _allTasks.value){
            listAllTasks  = _allTasks.value as List<Task>
        }

        return formatTasks(listAllTasks)
    }


}