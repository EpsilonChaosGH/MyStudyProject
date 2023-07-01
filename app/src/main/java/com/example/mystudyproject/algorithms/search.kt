package com.example.mystudyproject.algorithms

fun main() {

    val numbers = listOf(12, 22, 1, 5, 0, -2, 12, -5, 8)
    val sortedNumbers = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    println("linearSearch: ${linearSearch(numbers, 5)}")
    println("sortedNumbers: ${ binarySearch(sortedNumbers, 8) }")
    println("recursiveBinarySearch: ${recursiveBinarySearch(sortedNumbers, 8, 0, sortedNumbers.size - 1)}")

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

fun recursiveBinarySearch(list: List<Int>, item: Int, start: Int, end: Int): Int {
    val middle = (start + end) / 2

    if (list[middle] == item) return middle
    if (start == end) return -1

    return if (list[middle] > item) recursiveBinarySearch(list, item, start, middle - 1)
    else recursiveBinarySearch(list, item, middle + 1, end)
}