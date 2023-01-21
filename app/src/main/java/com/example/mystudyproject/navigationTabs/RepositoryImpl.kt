package com.example.mystudyproject.navigationTabs

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.util.*

class RepositoryImpl : Repository {

    private var number = 0

    private val numberFlow = MutableSharedFlow<Int>(
        replay = 0,
        extraBufferCapacity = 1,
        BufferOverflow.DROP_OLDEST
    )

    override suspend fun getRandomNumber() {
        number = Random().nextInt(100)
        loadNumber()
    }

    override suspend fun loadNumber() {
        numberFlow.emit(number)
    }

    override fun listenCurrentNumber(): Flow<Int> = numberFlow


}