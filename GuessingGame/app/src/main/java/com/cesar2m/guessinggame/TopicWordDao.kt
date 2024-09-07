package com.cesar2m.guessinggame

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update


@Dao
interface TopicWordDao {

    @Insert
    suspend fun save(topic: Topic)
    @Insert
    suspend fun saveAllTopics(listNewTopic: List<Topic>)

    @Insert
    suspend fun save(word: Word)
    @Insert
    suspend fun saveAllWords(listNewWords: List<Word>)

    @Delete
    fun delete(word: Word)

    @Update
    fun update(topic: Topic)


    @Query("SELECT * FROM word_table " +
            " WHERE topic_id  = :topicId ")
    fun findByTopicId(topicId: Long): LiveData<List<Word>>

    @Query("SELECT * FROM word_table ")
    fun findAllWords(): LiveData<List<Word>>

    @Query("SELECT * FROM topic_table " +
            " WHERE topic_name  = :topicName ")
    fun findByTopicName(topicName: String): Topic


    @Query("SELECT * FROM topic_table ")
    fun findAllTopic(): LiveData<List<Topic>>
}