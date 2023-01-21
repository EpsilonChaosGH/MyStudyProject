package com.example.mystudyproject

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mystudyproject.mvvm.DroidDetailsViewModel
import com.example.mystudyproject.mvvm.DroidsListViewModel
import com.example.mystudyproject.mvvm.Navigator
import com.example.mystudyproject.navigationTabs.FirstViewModel
import com.example.mystudyproject.navigationTabs.SecondViewModel

class ViewModelFactory(
    private val app: App
) : ViewModelProvider.Factory {


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = when (modelClass) {
            DroidsListViewModel::class.java -> DroidsListViewModel(app.droidsService)
            DroidDetailsViewModel::class.java -> DroidDetailsViewModel(app.droidsService)
            FirstViewModel::class.java -> FirstViewModel(app.repositoryImpl)
            SecondViewModel::class.java -> SecondViewModel(app.repositoryImpl)
            else -> {
                throw IllegalStateException("Unknown view model class")
            }
        }
        return viewModel as T
    }
}

fun Fragment.factory() = ViewModelFactory(requireContext().applicationContext as App)

fun Fragment.navigator() = requireActivity() as Navigator