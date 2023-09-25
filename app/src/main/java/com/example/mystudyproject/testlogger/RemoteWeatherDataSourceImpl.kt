package com.example.mystudyproject.testlogger


import com.example.mystudyproject.testlogger.model.CurrentWeatherResponse
import com.example.mystudyproject.testlogger.model.Result
import com.example.mystudyproject.testlogger.utils.mapResponseCodeToThrowable
import javax.inject.Inject

class RemoteWeatherDataSourceImpl @Inject constructor(
    private val currentWeatherService: CurrentWeatherService
) : RemoteWeatherDataSource {

    override suspend fun fetchWeatherData(
        city: String
    ): Result<CurrentWeatherResponse> =
        try {

            val response = currentWeatherService.getCurrentWeatherByCity(city)

            if (response.isSuccessful && response.body() != null) {
                val weatherData = response.body()!!
                Result.Success(data = weatherData)
            } else {
                val throwable = mapResponseCodeToThrowable(response.code())
                throw throwable
            }
        } catch (e: Exception) {
            throw e
        }

}