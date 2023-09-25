package com.example.mystudyproject.testlogger.di


import com.example.mystudyproject.testlogger.RemoteWeatherDataSource
import com.example.mystudyproject.testlogger.RemoteWeatherDataSourceImpl
import com.example.mystudyproject.testlogger.WeatherRepositoryImpl
import com.example.mystudyproject.testlogger.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class WeatherRepositoryModule {

    @Binds
    abstract fun bindWeatherRepository(
        weatherRepositoryImpl: WeatherRepositoryImpl
    ): WeatherRepository

    @Binds
    abstract fun bindRemoteWeatherDataSource(
        remoteWeatherDataSource: RemoteWeatherDataSourceImpl
    ): RemoteWeatherDataSource
}