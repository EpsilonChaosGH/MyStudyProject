package com.example.mystudyproject.algorithms

fun main() {

    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9)

    println(linearSearch(list, 3))
    println(binarySearch(list, 3))
    println(recursiveBinarySearch(list, 3, 0, list.size - 1))

}

fun linearSearch(list: List<Int>, item: Int): Int {
    for (i in list.indices) if (list[i] == item) return i
    return -1
}

fun binarySearch(list: List<Int>, item: Int): Int {
    var start = 0
    var end = list.size - 1
    var middle: Int

    while (start <= end) {
        middle = (start + end) / 2
        if (list[middle] == item) return middle
        if (list[middle] > item) end = middle - 1
        else start = middle + 1
    }
    return -1
}

fun recursiveBinarySearch(list: List<Int>, item: Int, start: Int, end: Int): Int {
    if (start > end) return -1

    val middle = (start + end) / 2
    if (list[middle] == item) return middle

    return if (list[middle] > item) recursiveBinarySearch(list, item, start, middle - 1)
    else recursiveBinarySearch(list, item, middle + 1, end)
}