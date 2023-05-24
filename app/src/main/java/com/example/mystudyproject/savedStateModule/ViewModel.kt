package com.example.mystudyproject.savedStateModule

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle
) : ViewModel() {


    private val _square = savedStateHandle.getLiveData("square", createSquare())
    val square: LiveData<Squares> = _square


    fun generateSquare() {
        _square.value = createSquare()
    }

    private fun createSquare(): Squares {
        return Squares(
            size = Random.nextInt(5, 11),
            colorProducer = { -Random.nextInt(0xffffff) }
        )
    }
}