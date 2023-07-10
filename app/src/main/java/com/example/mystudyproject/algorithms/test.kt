package com.example.mystudyproject.algorithms

import java.util.Collections

fun main() {

    val list = listOf(11, 6, 3, -2, 0, 22, 54, -7)

    println(bubbleSort1(list))
    println(selectionSort1(list))
    println(quickSort1(list))

    println(fibonacci(5))

}

fun fibonacci(item: Int): Int {
    if (item <= 1) return 1
    return item * fibonacci(item - 1)
}

fun bubbleSort1(list: List<Int>): List<Int> {
    var isSorted = false

    while (!isSorted) {
        isSorted = true
        for (i in 1 until list.size) {
            if (list[i - 1] > list[i]) {
                Collections.swap(list, i, i - 1)
                isSorted = false
            }
        }
    }
    return list
}

fun selectionSort1(list: List<Int>): List<Int> {
    for (i in list.indices) {
        var minIndex = i
        for (x in i + 1 until list.size) {
            if (list[minIndex] > list[x]) minIndex = x
        }
        Collections.swap(list, minIndex, i)
    }
    return list
}

fun quickSort1(list: List<Int>): List<Int> {
    if (list.size <= 1) return list

    val pivotIndex = list.size / 2
    val pivot = list[pivotIndex]
    val less = mutableListOf<Int>()
    val greater = mutableListOf<Int>()

    for (i in list.indices) {
        if (i == pivotIndex) continue
        if (list[i] < pivot) less.add(list[i])
        else greater.add(list[i])
    }

    return quickSort1(less) + pivot + quickSort1(greater)
}