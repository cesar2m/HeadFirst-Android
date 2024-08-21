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
    suspend fun save(word: Word)

    @Delete
    fun delete(word: Word)

    @Update
    fun update(topic: Topic)


    @Transaction
    @Query("SELECT * FROM word_table " +
            " WHERE topic_id  = :topicId " +
            " ORDER BY word DESC ")
    fun findByTopicId(topicId: Long): LiveData<Word>

    @Query("SELECT * FROM word_table ")
    fun findAllWords(): LiveData<Word>

    @Query("SELECT * FROM topic_table ORDER BY topic_name DESC ")
    fun findAllTopic(): LiveData<Topic>
}