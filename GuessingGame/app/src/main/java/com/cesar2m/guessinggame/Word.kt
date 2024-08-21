package com.cesar2m.guessinggame

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "word_table",
    foreignKeys = [ForeignKey(
        entity = Topic::class, // Entidad padre
        parentColumns = ["topicId"], // Columna de la entidad padre
        childColumns = ["topic_id"], // Columna de la entidad hija
        onDelete = ForeignKey.CASCADE // Comportamiento en caso de eliminación
    )])
data class Word (

    @PrimaryKey(autoGenerate = true)
    var wordId: Long = 0L,

    @ColumnInfo(name="word")
    var word: String = "",


    @ColumnInfo(name="topic_id")
    var topicId: Long = 0L

)