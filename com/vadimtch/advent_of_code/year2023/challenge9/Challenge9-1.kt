package com.vadimtch.advent_of_code.year2023.challenge9

import java.io.File

class Sequence(
    private val numbers: List<Int>
) {
    constructor(description: String) : this(
        description
            .split(" ")
            .map { it.toInt() }
    )

    fun predictNext(): Int {
        val deltas = calculateDeltas()

        return if (deltas.all { it == 0 }) {
            numbers.last()
        } else {
            val increment = Sequence(deltas).predictNext()
            numbers.last() + increment
        }
    }

    fun predictPrevious(): Int {
        val deltas = calculateDeltas()

        return if (deltas.all { it == 0 }) {
            numbers.first()
        } else {
            val increment = Sequence(deltas).predictPrevious()
            numbers.first() - increment
        }
    }

    private fun calculateDeltas(): List<Int> {
        return numbers
            .mapIndexed { i, n -> if (i == 0) 0 else n -  numbers[i - 1] }
            .drop(1)
    }
}

fun loadSequences(): List<Sequence> {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge9/Challenge9.txt")
    return file.readLines()
        .map { Sequence(it) }
}

fun main() {
    val sequences = loadSequences()

    val nextNumbers = sequences.map { it.predictNext() }
    println(nextNumbers.sum())
}
