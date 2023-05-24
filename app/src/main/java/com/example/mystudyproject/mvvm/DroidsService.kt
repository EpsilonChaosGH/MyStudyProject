package com.example.mystudyproject.mvvm

import java.util.*
import java.util.concurrent.Callable
import kotlin.collections.ArrayList

typealias DroidsListener = (droids: List<Droid>) -> Unit

class DroidService {

    private val listeners = mutableSetOf<DroidsListener>()
    private var droids = mutableListOf<Droid>()
    private var loaded = false

    fun loadDroid(): Task<Unit> = SimpleTask {
        Thread.sleep(1000)
        droids = (1..50).map {
            Droid(
                it.toLong(),
                "https://images.squarespace-cdn.com/content/v1/598c9550e3df281aec02cbe6/" +
                        "1512245659979-6FYF4EQ5YOF3ZQYQRMZC/android_attitude.png?format=1000w",
                "name $it",
                "company $it"
            )
        }.toMutableList()
        loaded = true
        notifyChanges()
    }


    fun getById(id: Long): Task<DroidDetails> = SimpleTask(Callable {
        Thread.sleep(1000)
        val droid = droids.firstOrNull { it.id == id } ?: throw DroidNotFoundException()
        return@Callable DroidDetails(droid, "DETAILS")
    })


    fun deleteDroid(droid: Droid): Task<Unit> = SimpleTask {
        Thread.sleep(1000)
        val indexToDelete = droids.indexOfFirst { it.id == droid.id }
        if (indexToDelete != -1) {
            droids = ArrayList(droids)
            droids.removeAt(indexToDelete)
        }
        notifyChanges()
    }

    fun moveDroid(droid: Droid, moveBy: Int): Task<Unit> = SimpleTask(Callable {
        Thread.sleep(1000)
        val oldIndex = droids.indexOfFirst { it.id == droid.id }
        if (oldIndex == -1) return@Callable
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 && newIndex >= droids.size) return@Callable
        droids = ArrayList(droids)
        Collections.swap(droids, oldIndex, newIndex)
        notifyChanges()
    })

    fun addListener(listener: DroidsListener) {
        listeners.add(listener)
        if (loaded) {
            listener.invoke(droids)
        }
    }

    fun removeListener(listener: DroidsListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        if (!loaded) return
        listeners.forEach { it.invoke(droids) }
    }
}



