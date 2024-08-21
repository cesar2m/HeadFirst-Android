package com.cesar2m.guessinggame

import androidx.room.Embedded
import androidx.room.Relation

data class TopicWord (

    @Embedded
    val topic: Topic,
    @Relation(
        parentColumn = "topicId",
        entityColumn = "topicId"
    )
    val words: List<Word>
)

