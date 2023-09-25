package com.example.mystudyproject.testlogger

import com.example.mystudyproject.testlogger.model.CurrentWeatherResponse
import com.example.mystudyproject.testlogger.model.Result


interface RemoteWeatherDataSource {

    suspend fun fetchWeatherData(
        city: String
    ): Result<CurrentWeatherResponse>
}