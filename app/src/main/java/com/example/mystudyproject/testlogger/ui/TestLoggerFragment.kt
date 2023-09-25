package com.example.mystudyproject.testlogger.ui

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import com.example.mystudyproject.R
import com.example.mystudyproject.databinding.FragmentTestloggerBinding
import com.example.mystudyproject.testlogger.WeatherRepository
import com.example.mystudyproject.testlogger.model.CurrentWeatherResponse
import com.example.mystudyproject.testlogger.model.ErrorType
import com.example.mystudyproject.testlogger.model.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class TestLoggerFragment : Fragment(R.layout.fragment_testlogger) {

    @Inject lateinit var weatherRepository: WeatherRepository

    private lateinit var binding: FragmentTestloggerBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentTestloggerBinding.bind(view)

        binding.progressBar.isVisible = false

        binding.successButton.setOnClickListener {
            lifecycle.coroutineScope.launch {
                binding.progressBar.isVisible = true
                processResult(weatherRepository.fetchWeatherData("tver"))
                binding.progressBar.isVisible = false
            }
        }

        binding.errorButton.setOnClickListener {
            lifecycle.coroutineScope.launch {
                binding.progressBar.isVisible = true
                processResult(weatherRepository.fetchWeatherData("qweqaasdqwedaws12"))
                binding.progressBar.isVisible = false
            }
        }

        val res: Result<String> = Result.Error(ErrorType.GENERIC)

    }

    private fun processResult(result: Result<CurrentWeatherResponse>) {
        when (result) {
            is Result.Success -> {
                val weatherData = result.data
                binding.textView.text = weatherData.main.feelsLike.toString()
            }

            is Result.Error -> {
                binding.textView.text = result.errorType.name
            }
        }
    }
}