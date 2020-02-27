package com.example.practicaskotlin.data.model.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.practicaskotlin.data.model.room.Data

@Dao
interface DataDao {

    @Insert
     suspend fun insertData(data: Data)

    @Query("SELECT * FROM dictionary")
    fun getAllUsers(): LiveData<List<Data>>

    @Delete
    suspend fun deleteData(data: Data)

    @Query("SELECT * FROM dictionary WHERE dataEnglish = :wordEnglish" )
    suspend fun existsWord(wordEnglish: String): Data?

    @Query("UPDATE dictionary SET dataEnglish = :wordEnglish  WHERE id = :id ")
    suspend fun editWords(id: Int , wordEnglish: String): Int?

}