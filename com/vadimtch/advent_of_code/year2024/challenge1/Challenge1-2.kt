package com.vadimtch.advent_of_code.year2024.challenge1

fun main() {
    val (left, right) = readLists()

    val rightCounts = right.groupingBy { it }.eachCount()

    val similarity = left.fold(0) { sum, value ->
        val count = rightCounts[value] ?: 0
        if (count > 0) {
            sum + value * count
        } else {
            sum
        }
    }

    println(similarity)
}
