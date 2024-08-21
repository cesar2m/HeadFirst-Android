package com.cesar2m.guessinggame

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "topic_table")
data class Topic(

    @PrimaryKey(autoGenerate = true)
    var topicId: Long = 0L,

    @ColumnInfo(name = "topic_name")
    var topicName: String = "",

    @ColumnInfo(name = "activo")
    var activo: Boolean = true

)
