package com.vadimtch.advent_of_code.year2023.challenge9

fun main() {
    val sequences = loadSequences()

    val sum = sequences.sumOf { it.predictPrevious() }

    println(sum)
}
