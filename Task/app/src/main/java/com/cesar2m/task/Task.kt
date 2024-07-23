package com.cesar2m.task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "task_table")
data class Task (

    @PrimaryKey(autoGenerate = true)
    var taskId: Long = 0L,

    @ColumnInfo(name = "taskl_name")
    var taskName: String = "",

    @ColumnInfo(name = "taskl_done")
    var taskDone: Boolean = false
)