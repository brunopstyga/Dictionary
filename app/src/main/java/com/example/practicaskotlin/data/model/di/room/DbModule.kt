package com.example.practicaskotlin.data.model.di.room

import android.app.Application
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.practicaskotlin.data.model.room.Data
import com.example.practicaskotlin.data.model.room.DataDao
import com.example.practicaskotlin.data.model.room.DataRoomDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {


    @Provides
    @Singleton
    internal fun provideDatabase(context: Application): DataRoomDatabase {
        return Room.databaseBuilder<DataRoomDatabase>(
            context.applicationContext,
            DataRoomDatabase::class.java,
            "dictionary")
            .build()
    }

    @Provides
    @Singleton
    internal fun provideDataDao(roomDatabase: DataRoomDatabase): DataDao {
        return roomDatabase.dataDao()
    }
}