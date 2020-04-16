package com.example.practicaskotlin.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.practicaskotlin.data.model.room.Data
import com.example.practicaskotlin.data.model.room.DataDao
import javax.inject.Inject

class DataRepository @Inject constructor (private val dataDao: DataDao?){

    val allData: LiveData<List<Data>> = dataDao!!.getAllUsers()

    private val _floatButton = MutableLiveData<String>()

    suspend fun insterData(data: Data){
        dataDao!!.insertData(data)
    }

    suspend fun delete(data: Data){
        dataDao!!.deleteData(data)
    }

    suspend fun existsWord(dataWord: String): Boolean {
        return (dataDao!!.existsWord(dataWord) != null)
    }

    suspend fun editWords(id: Int, dataEnglish: String) {
        dataDao!!.editWords(id,dataEnglish)
    }
    
}