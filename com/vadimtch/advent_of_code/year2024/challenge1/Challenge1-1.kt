package com.vadimtch.advent_of_code.year2024.challenge1

import java.io.File
import kotlin.math.abs

fun readLists(): Pair<List<Int>, List<Int>> {
    val file = File("com/vadimtch/advent_of_code/year2024/challenge1/Challenge1.txt")

    val left = mutableListOf<Int>()
    val right = mutableListOf<Int>()
    file.readLines().forEach { line ->
        val parts = line.split(" ").filter { it.isNotEmpty() }
        left.add(parts[0].toInt())
        right.add(parts[1].toInt())
    }

    return Pair(left, right)
}

fun main() {
    val (left, right) = readLists()

    val leftSorted = left.sorted()
    val rightSorted = right.sorted()

    val diff = leftSorted.foldIndexed(0) { i, sum, _ ->
        sum + abs(leftSorted[i] - rightSorted[i])
    }

    println(diff)
}
