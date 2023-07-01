package com.example.mystudyproject.algorithms

import java.util.*

fun main() {

    val numbers = listOf(12, 11, 12, -32, 0, -2, 6, 23)

    selectionSort(numbers)
    bubbleSort(numbers)
    println(quickSort(numbers))
}

fun selectionSort(list: List<Int>) {
    for (i in list.indices) {
        var minIndex = i
        for (x in i + 1 until list.size) {
            if (list[minIndex] > list[x]) minIndex = x
        }
        Collections.swap(list, i, minIndex)
    }
    println(list)
}

fun bubbleSort(list: List<Int>) {
    var isSorted = false
    while (!isSorted) {
        isSorted = true
        for (i in 1 until list.size) {
            if (list[i] < list[i - 1]) {
                Collections.swap(list, i, i - 1)
                isSorted = false
            }
        }
    }
    println(list)
}

fun quickSort(list: List<Int>): List<Int> {
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
    return quickSort(less) + pivot + quickSort(greater)
}