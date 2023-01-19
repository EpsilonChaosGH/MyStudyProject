package com.example.mystudyproject.algorithms

import java.util.*

fun main() {
    val numbers = listOf(12, 11, 12, -32, 0, -2, 6, 23)

    var isSorted = false

    while (!isSorted) {
        isSorted = true
        for (i in 1 until numbers.size) {
            if (numbers[i] < numbers[i - 1]) {
                Collections.swap(numbers, i, i - 1)
                isSorted = false
            }
        }
    }

    for (i in numbers.indices) {
        var minIndex = i
        for (x in i + 1 until numbers.size) if (numbers[minIndex] > numbers[x]) minIndex = x
        if (numbers[minIndex] != numbers[i]) {
            Collections.swap(numbers, minIndex, i)
        }
    }

    println(numbers)
}