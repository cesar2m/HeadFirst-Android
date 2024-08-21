package com.cesar2m.guessinggame

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Word::class, Topic::class], version = 1, exportSchema = false)
abstract class GuessingGameDataBase: RoomDatabase() {

    abstract val topicWordDao: TopicWordDao

    companion object{
        @Volatile
        private var INSTANCE: GuessingGameDataBase? = null

        fun getInstance(context: Context): GuessingGameDataBase{
            synchronized(this){
                var instance = INSTANCE
                if( null == instance ){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        GuessingGameDataBase::class.java,
                        "guessinggame_database"
                    ).build()
                    INSTANCE = instance
                }
                return instance
            }
        }

    }

}