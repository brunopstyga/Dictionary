package com.example.practicaskotlin.data.model.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract  class DataRoomDatabase : RoomDatabase() {

    abstract fun dataDao(): DataDao

    companion object {
        @Volatile
        private var dataRoomIntance : DataRoomDatabase? = null;
    fun getDatabase(context: Context):DataRoomDatabase? {
     if (dataRoomIntance == null){
         synchronized(DataRoomDatabase::class.java){
             if(dataRoomIntance == null){
                 dataRoomIntance = Room.databaseBuilder<DataRoomDatabase>(
                     context.applicationContext,
                     DataRoomDatabase::class.java,
                     "dictionary")
                     .build()
             }
         }
     }
        return dataRoomIntance
    }

    }



}