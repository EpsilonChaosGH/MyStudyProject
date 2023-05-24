package com.example.mystudyproject.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mystudyproject.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DroidDetailsViewModel @Inject constructor(
    private val droidService: DroidService
) : BaseViewModel() {

    private val _state = MutableLiveData<State>()
    val state: LiveData<State> = _state

    private val _actionShowToast = MutableLiveData<Event<Int>>()
    val actionShowToast: LiveData<Event<Int>> = _actionShowToast

    private val _actionGoBack = MutableLiveData<Event<Unit>>()
    val actionGoBack: LiveData<Event<Unit>> = _actionGoBack

    private val currentState: State get() = state.value!!

    init {
        _state.value = State(EmptyResult(), false)
    }

    fun loadDroid(droidId: Long) {
        if (currentState.droidDetailsResult is SuccessResult) return

        _state.value = currentState.copy(droidDetailsResult = PendingResult())

        droidService.getById(droidId)
            .onSuccess {
                _state.value = currentState.copy(droidDetailsResult = SuccessResult(it))
            }
            .onError {
                _actionShowToast.value = Event(R.string.cant_load_droid_details)
                _actionGoBack.value = Event(Unit)
            }
            .autoCancel()
    }

    fun deleteDroid() {
        val droidDetailsResult = currentState.droidDetailsResult
        if (droidDetailsResult !is SuccessResult) return
        _state.value = currentState.copy(deleteInProgress = true)
        droidService.deleteDroid(droidDetailsResult.data.droid)
            .onSuccess {
                _actionShowToast.value = Event(R.string.droid_has_been_deleted)
                _actionGoBack.value = Event(Unit)
            }
            .onError {
                _state.value = currentState.copy(deleteInProgress = false)
                _actionShowToast.value = Event(R.string.cant_delete_droid)
            }
            .autoCancel()
    }

    data class State(
        val droidDetailsResult: Result<DroidDetails>,
        private val deleteInProgress: Boolean
    ) {
        val showContent: Boolean get() = droidDetailsResult is SuccessResult
        val showProgress: Boolean get() = droidDetailsResult is PendingResult || deleteInProgress
        val enableDeleteBottom: Boolean get() = !deleteInProgress
    }
}
