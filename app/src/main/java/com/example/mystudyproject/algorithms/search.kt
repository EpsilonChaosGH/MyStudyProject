package com.example.mystudyproject.algorithms

fun main() {

    val numbers = listOf(12, 22, 1, 5, 0, -2, 12, -5, 8)
    val sortedNumbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println(linearSearch(numbers, 5))
    println(binarySearch(sortedNumbers, 10))

}

fun linearSearch(list: List<Int>, element: Int): Int {
    for ((i, v) in list.withIndex()) {
        if (v == element) return i
    }
    return -1
}

fun binarySearch(list: List<Int>, element: Int): Int {
    var start = 0
    var end = list.size - 1
    var middle: Int
    while (start <= end) {
        middle = (start + end) / 2
        if (list[middle] == element) return middle
        if (list[middle] > element) {
            end = middle - 1
        } else {
            start = middle + 1
        }
    }
    return -1
}