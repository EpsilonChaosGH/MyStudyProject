package com.example.mystudyproject.mvvm

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mystudyproject.App
import com.example.mystudyproject.MenuFragment

class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            DroidsListViewModel::class.java -> {
                DroidsListViewModel(app.droidsService)
            }
            DroidDetailsViewModel::class.java -> {
                DroidDetailsViewModel(app.droidsService)
            }
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)

fun Fragment.navigator() = requireActivity() as Navigator