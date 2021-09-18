package com.example.myapplication.room

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Item
import kotlinx.coroutines.launch

class WordViewModel(private val repository: WordRepository) : ViewModel() {

    val allName: LiveData<List<Item>> = repository.allName.asLiveData()
    val allAge: LiveData<List<Item>> = repository.allAge.asLiveData()
    val allBreed: LiveData<List<Item>> = repository.allBreed.asLiveData()

    fun insert(word: Item) = viewModelScope.launch {
        repository.insert(word)
    }

    fun update(word: Item) = viewModelScope.launch {
        repository.update(word)
    }

    fun delete(word: Item) = viewModelScope.launch {
        repository.delete(word)
    }
}

class WordViewModelFactory(private val repository: WordRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WordViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return WordViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}