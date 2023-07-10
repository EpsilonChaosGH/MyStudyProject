package com.example.mystudyproject.algorithms

import kotlin.random.Random


fun main() {

    var maxResult = 0

    val size = 1000

    for(i in 1..100){
        val list = (0..size).toMutableList()

        val result = binarySearchTest(list, Random.nextInt(size))
        println(result)

        if (result > maxResult) maxResult = result
    }

    println(maxResult)
}

fun binarySearchTest(list: List<Int>, item: Int): Int {
    var counter = 0
    var start = 0
    var end = list.size - 1
    var middle: Int

    while (start <= end) {
        counter++
        middle = (start + end) / 2
        if (list[middle] == item) return counter
        if (list[middle] > item) end = middle - 1
        else start = middle + 1
    }
    return -1
}