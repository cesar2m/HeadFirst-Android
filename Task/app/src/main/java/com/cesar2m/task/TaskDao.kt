package com.cesar2m.task

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {

    @Insert
    fun insert(task: Task)

    @Update
    fun update(tasks: Task)

    @Delete
    fun delete(tasK: Task)

    @Query("SELECT * FROM task_table WHERE taskId = :taskId ")
    fun get (taskId: Long):  LiveData<Task>

    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getAll(): LiveData<List<Task>>
}