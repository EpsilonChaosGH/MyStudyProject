package com.example.mystudyproject.testlogger


import com.example.mystudyproject.testlogger.model.CurrentWeatherResponse
import com.example.mystudyproject.testlogger.model.Result
import com.example.mystudyproject.testlogger.utils.mapThrowableToErrorType
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val remoteWeatherDataSource: RemoteWeatherDataSource,
   // private val logger: Logger
) : WeatherRepository {

     override suspend fun fetchWeatherData(city: String): Result<CurrentWeatherResponse> =
        try {
            remoteWeatherDataSource.fetchWeatherData(city)
        } catch (throwable: Throwable) {
            val errorType = mapThrowableToErrorType(throwable)
           // logger.logException(throwable)
            Result.Error(errorType)
        }


}
