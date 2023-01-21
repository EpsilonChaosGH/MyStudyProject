package com.example.mystudyproject.navigationTabs

import kotlinx.coroutines.flow.Flow

interface Repository {

    fun listenCurrentNumber(): Flow<Int>

    suspend fun loadNumber()

    suspend fun getRandomNumber()

}
