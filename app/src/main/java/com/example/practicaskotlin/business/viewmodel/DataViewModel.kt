package com.example.practicaskotlin.business.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.example.practicaskotlin.data.model.room.Data
import com.example.practicaskotlin.repository.DataRepository
import kotlinx.android.synthetic.main.fragment_edit.*
import kotlinx.coroutines.launch
import javax.inject.Inject

class DataViewModel @Inject constructor( application: Application, val dataRepository : DataRepository) : AndroidViewModel(application)  {

    private val context = getApplication<Application>().applicationContext


    val editTextEnglish = MutableLiveData<String>()
    val editTextSpanish = MutableLiveData<String>()

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