package com.example.practicaskotlin.business.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.practicaskotlin.data.model.room.Data
import com.example.practicaskotlin.data.model.room.DataRoomDatabase
import com.example.practicaskotlin.repository.DataRepository
import kotlinx.coroutines.launch

class DataViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val dataRepository: DataRepository

    init {
        val dataDao = DataRoomDatabase.getDatabase(application)?.dataDao()
       dataRepository = DataRepository(dataDao)

    }

    fun inserData(data: Data){
    viewModelScope.launch {
    dataRepository.insterData(data)
    }

    }

    fun dataGet(): LiveData<List<Data>>{
      return  dataRepository.allData
    }

    fun deleteData(data: Data){
        viewModelScope.launch {
            dataRepository.delete(data)
        }
    }

    fun existsWord(word: String): LiveData<Boolean> = liveData {
        emit(dataRepository.existsWord(word))
    }

    fun editWords(id: Int, dataEnglish: String){
        viewModelScope.launch {
            dataRepository.editWords(id, dataEnglish)
        }
    }

}