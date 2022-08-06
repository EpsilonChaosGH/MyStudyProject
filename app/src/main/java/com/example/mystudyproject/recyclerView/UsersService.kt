package com.example.mystudyproject.recyclerView

import java.util.*

typealias UsersListener = (users: List<User>) -> Unit

class UsersService() {

    private val listeners = mutableSetOf<UsersListener>()
    private var users = mutableListOf<User>()

    init {
        users = (1..100).map {
            User(
                it.toLong(),
                "https://images.unsplash.com/photo-1488426862026-3ee34a7d66df?crop=entropy&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHwxNjI0MDE0ODE0&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=referral&utm_source=unsplash_source&w=800",
                "name $it",
                "company $it"
            )
        }.toMutableList()
    }

    fun getUsers(): List<User> {
        return users
    }

    fun addListener(listener: UsersListener) {
        listeners.add(listener)
        listener.invoke(users)
    }

    fun removeListener(listener: UsersListener) {
        listeners.remove(listener)
    }

    private fun notifyChanges() {
        listeners.forEach { it.invoke(users) }
    }

    fun deleteUser(user: User) {
        val indexToDelete = users.indexOfFirst { it.id == user.id }
        if (indexToDelete != -1) {
            users.removeAt(indexToDelete)
        }
        notifyChanges()
    }

    fun moveUser(user: User, moveBy: Int) {
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 && newIndex >= users.size) return
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()
    }

}