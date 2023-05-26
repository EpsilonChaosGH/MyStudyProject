package com.example.mystudyproject.hilt

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystudyproject.hilt.retrofit.RetrofitMainSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HiltTestViewModel @Inject constructor(
    private val retrofitMainSource: RetrofitMainSource,
    private val settings: Settings,
    val repository: Repository,
    private val dataBase: DataBase
) : ViewModel() {

    private var _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    init {
        getItem()
    }

    private fun getItem() {
        viewModelScope.launch {
            _state.value = State(
                retrofitMainSource.getItemByName("m4a1").data.itemsByName[0].name,
                settings.settings,
                repository.repositoryValue,
                dataBase.dataBase
            )
        }
    }
}

data class State(
    var retrofit: String,
    var settings: String,
    var repo: String,
    var db: String,
)