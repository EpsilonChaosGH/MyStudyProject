package com.example.mystudyproject.navigationTabs

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FirstViewModel(
    private val repository: Repository
) : ViewModel() {

    private val _state: MutableStateFlow<Int> = MutableStateFlow(0)
    val state: StateFlow<Int> = _state

    init {
        listenCurrentNumber()
        loadNumber()
    }

    suspend fun getRandomNumber() {
        repository.getRandomNumber()
    }

    private fun listenCurrentNumber() {
        viewModelScope.launch {
            repository.listenCurrentNumber().collect {
                _state.value = it
            }
        }
    }

    private fun loadNumber() {
        viewModelScope.launch {
            repository.loadNumber()
        }
    }
}