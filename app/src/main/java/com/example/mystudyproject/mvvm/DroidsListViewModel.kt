package com.example.mystudyproject.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mystudyproject.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class DroidListItem(
    val droid: Droid,
    val isInProgress: Boolean
)

@HiltViewModel
class DroidsListViewModel @Inject constructor(
    private val droidService: DroidService
) : BaseViewModel(), DroidActionListener {

    private val _droid = MutableLiveData<Result<List<DroidListItem>>>()
    val droid: LiveData<Result<List<DroidListItem>>> = _droid

    private val _actionShowDetails = MutableLiveData<Event<Droid>>()
    val actionShowDetails: LiveData<Event<Droid>> = _actionShowDetails

    private val _actionShowToast = MutableLiveData<Event<Int>>()
    val actionShowToast: LiveData<Event<Int>> = _actionShowToast

    private val droidIdsInProgress = mutableSetOf<Long>()
    private var droidResult: Result<List<Droid>> = EmptyResult()
        set(value) {
            field = value
            notifyUpdates()
        }

    private fun addProgressTo(droid: Droid) {
        droidIdsInProgress.add(droid.id)
        notifyUpdates()
    }

    private fun removeProgressFrom(droid: Droid) {
        droidIdsInProgress.remove(droid.id)
        notifyUpdates()
    }

    private fun isInProgress(droid: Droid): Boolean {
        return droidIdsInProgress.contains(droid.id)
    }

    private fun notifyUpdates() {
        _droid.postValue(droidResult.map { droids ->
            droids.map { droid -> DroidListItem(droid, isInProgress(droid)) }
        })
    }

    private val listener: DroidsListener = {
        droidResult = if (it.isEmpty()) {
            EmptyResult()
        } else {
            SuccessResult(it)
        }
    }

    init {
        droidService.addListener(listener)
        loadDroids()
    }

    override fun onCleared() {
        super.onCleared()
        droidService.removeListener(listener)
    }

    private fun loadDroids() {
        droidResult = PendingResult()
        droidService.loadDroid()
            .onError {
                droidResult = ErrorResult(it)
            }
            .autoCancel()
    }

    override fun onDroidMove(droid: Droid, moveBy: Int) {
        if (isInProgress(droid)) return
        addProgressTo(droid)
        droidService.moveDroid(droid, moveBy)
            .onSuccess {
                removeProgressFrom(droid)
            }
            .onError {
                removeProgressFrom(droid)
                _actionShowToast.value = Event(R.string.cant_move_droid)
            }
            .autoCancel()
    }

    override fun onDroidDelete(droid: Droid) {
        if (isInProgress(droid)) return
        addProgressTo(droid)
        droidService.deleteDroid(droid)
            .onSuccess {
                removeProgressFrom(droid)
            }
            .onError {
                removeProgressFrom(droid)
                _actionShowToast.value = Event(R.string.cant_delete_droid)
            }
            .autoCancel()
    }

    override fun onDroidDetails(droid: Droid) {
        _actionShowDetails.value = Event(droid)
    }
}