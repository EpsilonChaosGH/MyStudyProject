package com.example.mystudyproject.algorithms

import java.util.Collections

fun main() {

    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)

    println(linearSearch11(list, 7))
    println(binarySearch11(list, 7))
    println(recursiveBinarySearch11(list, 8, 0, list.size - 1))

    val list2 = listOf(11, 2, 6, -2, 0, 5, -23)

    println(bubbleSearch11(list2))
    println(selectionSort11(list2))
}

fun linearSearch11(list: List<Int>, item: Int): Int {
    for (i in list.indices) {
        if (list[i] == item) return i
    }
    return -1
}

fun binarySearch11(list: List<Int>, item: Int): Int {
    var start = 0
    var end = list.size - 1
    var middle: Int

    while (start <= end) {
        middle = (start + end) / 2
        if (list[middle] == item) return middle
        if (list[middle] > item) {
            end = middle - 1
        } else {
            start = middle + 1
        }
    }
    return -1
}

fun bubbleSearch11(list: List<Int>): List<Int> {
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
    return list
}

fun recursiveBinarySearch11(list: List<Int>, item: Int, start: Int, end: Int): Int {
    val middle = (start + end) / 2

    if (list[middle] == item) return middle
    if (start == end) return -1

    return if (list[middle] > item) recursiveBinarySearch(list, item, start, middle - 1)
    else recursiveBinarySearch(list, item, middle + 1, end)
}

fun selectionSort11(list: List<Int>): List<Int> {
    for (i in list.indices) {
        var minIndex = i
        for (x in i + 1 until list.size) {
            if (list[x] < list[minIndex]) minIndex = x
        }
        Collections.swap(list, minIndex, i)
    }
    return list
}