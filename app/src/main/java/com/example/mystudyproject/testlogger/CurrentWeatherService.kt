package com.example.mystudyproject.testlogger


import com.example.mystudyproject.testlogger.model.CurrentWeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherService {

    @GET("weather?")
    suspend fun getCurrentWeatherByCity(
        @Query("q") city: String,
        @Query("appid") appId: String = "a7a3f0914c35285059a4430b6410fef6",
    ): Response<CurrentWeatherResponse>
}