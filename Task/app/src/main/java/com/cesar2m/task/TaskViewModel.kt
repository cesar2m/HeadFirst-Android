package com.cesar2m.task


import androidx.annotation.NonNull
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
import java.util.stream.Collectors


class TaskViewModel(val dao: TaskDao) : ViewModel() {

    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask: LiveData<Long?>
        get() = _navigateToTask

    var newTaskName = ""
    val tasks = dao.getAll()

    var i: Long = 0
    val arrayListTask: ArrayList<Task> = arrayListOf()

    private var _allTasks = MutableLiveData<List<Task>>()
    val allTasks : LiveData<List<Task>> get() = _allTasks


    fun addTask(){

        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }


    }

    fun onTaskClicked(taskId: Long){
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated(){
        _navigateToTask.value = null
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

    /**
     * Implementación de una versión que no pude importar para está versión de Android.
     * Se supone que era para la lifecicle 2.6.0 al menos, pero nunca cargo, solo en versiones
     * menores como la que viene de ejemplo en el libro. NOTA: Tome la función de la clase original
     * y la transformé a Kotlin.
     */
    fun <X, Y> map(
        source: LiveData<X>,
        mapFunction: (X?) -> Y
    ): LiveData<Y> {
        val result = MediatorLiveData<Y>()
        result.addSource(source, Observer { x ->
            result.value = mapFunction(x)
        })
        return result
    }

}