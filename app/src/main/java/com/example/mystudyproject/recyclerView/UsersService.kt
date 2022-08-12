package com.example.mystudyproject.recyclerView

import android.util.Log
import android.widget.Toast
import java.util.*
import kotlin.collections.ArrayList

typealias UsersListener = (users: List<User>) -> Unit

class UsersService {

    private val listeners = mutableSetOf<UsersListener>()
    private var users = mutableListOf<User>()

    init {
        users = (1..50).map {
            User(
                it.toLong(),
                "https://images.unsplash.com/photo-1488426862026-3ee34a7d66df?crop=entropy" +
                        "&cs=tinysrgb&fit=crop&fm=jpg&h=600&ixid=MnwxfDB8MXxyYW5kb218fHx8fHx8fHw" +
                        "xNjI0MDE0ODE0&ixlib=rb-1.2.1&q=80&utm_campaign=api-credit&utm_medium=re" +
                        "ferral&utm_source=unsplash_source&w=800",
                "name $it",
                "company $it"
            )
        }.toMutableList()
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
            users = ArrayList(users)
            users.removeAt(indexToDelete)
        }
        notifyChanges()
    }

    fun moveUser(user: User, moveBy: Int) {
        val oldIndex = users.indexOfFirst { it.id == user.id }
        if (oldIndex == -1) return
        val newIndex = oldIndex + moveBy
        if (newIndex < 0 && newIndex >= users.size) return
        users = ArrayList(users)
        Collections.swap(users, oldIndex, newIndex)
        notifyChanges()
    }

}


typealias TestListener = (List<Test>) -> Unit

class Test(
    val x: Int
    )

fun main() {

    val listeners = mutableSetOf<TestListener>()
    var tests = mutableListOf<Test>(Test(1), Test(2), Test(3))

    fun addListener(listener: TestListener) {
        listeners.add(listener)
        listener.invoke(tests)
    }

    val testListener: TestListener = {
    var newTestList = it
    }

    addListener(testListener)



//    val list = listOf("1", "2", "3", "4", "5")
//
//    fun<T, R>Collection<T>.recycle(r: R, function: (T, R) -> T): MutableCollection<T>{
//        val newCollection = mutableListOf<T>()
//        for (i in this){
//            newCollection.add(function(i,r))
//        }
//        return newCollection
//    }
//
//    val listCopy = list.recycle(8){a, b -> a + b}
//
//    println(listCopy)
//
//
//    fun <T, R> Collection<T>.fold(initial: R, combine: (acc: R, nextElement: T) -> R): R {
//
//        var accumulator: R = initial
//        for (element: T in this) {
//            accumulator = combine(accumulator, element)
//        }
//        return accumulator
//    }
//
//    val sum: (Int, Int) -> Int = { x: Int, y: Int -> x + y }
//
//    val items = listOf(1, 2, 3, 4, 5)
//
//
//    items.fold(0) { acc: Int, i: Int ->
//        print("acc = $acc, i = $i, ")
//        val result = acc + i
//        println("result = $result")
//        result
//    }
//
//    // Типы параметров в лямбде необязательны, если они могут быть выведены:
//    val joinedToString = items.fold("Elements:", { acc, i -> acc + " " + i })
//
//    // Ссылки на функции также могут использоваться для вызовов функций высшего порядка:
//    val product = items.fold(1, Int::times)
//
//    println("joinedToString = $joinedToString")
//    println("product = $product")
//

}