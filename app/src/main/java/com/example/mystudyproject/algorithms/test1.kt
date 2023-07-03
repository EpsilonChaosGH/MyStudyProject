package com.example.mystudyproject.algorithms


fun main() {

    val list = listOf(1, 2, 3, 4, 5, 6, 7, 8)
    val list2 = listOf(12, 12, 5, 4, -5, 6, 7, 0)

    println(linearSearch3(list, 8))
    println(binarySearch3(list, 8))
    println(recursiveBinarySearch3(list, 8, 0, list.size - 1))
    println(quickSort3(list2))

}

fun linearSearch3(list: List<Int>, item: Int): Int {
    for (i in list.indices) if (list[i] == item) return i
    return -1
}

fun binarySearch3(list: List<Int>, item: Int): Int {
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

fun recursiveBinarySearch3(list: List<Int>, item: Int, start: Int, end: Int): Int {
    val middle = (start + end) / 2

    if (list[middle] == item) return middle
    if (start > end) return -1
    return if (list[middle] > item) recursiveBinarySearch3(list, item, start, middle - 1)
    else recursiveBinarySearch3(list, item, middle + 1, end)
}

fun quickSort3(list: List<Int>): List<Int> {
    if (list.size <= 1) return list
    val pivotIndex = list.size / 2
    val pivot = list[pivotIndex]
    val less = mutableListOf<Int>()
    val greater = mutableListOf<Int>()

    for (i in list.indices) {
        if (pivotIndex == i) continue
        if (pivot > list[i]) less.add(list[i])
        else greater.add(list[i])
    }
    return quickSort3(less) + pivot + quickSort3(greater)
}