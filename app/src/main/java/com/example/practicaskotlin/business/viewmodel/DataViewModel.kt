package com.example.practicaskotlin.business.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.practicaskotlin.data.model.room.Data
import com.example.practicaskotlin.data.model.room.DataRoomDatabase
import com.example.practicaskotlin.repository.DataRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DataViewModel @Inject constructor( val dataRepository : DataRepository) : ViewModel() {


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