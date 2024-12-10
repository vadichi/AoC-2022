package com.vadimtch.advent_of_code.year2024.challenge7

import java.io.File

enum class Operator(val apply: (Long, Long) -> Long) {
    Add({ a, b -> a + b }),
    Multiply({ a, b -> a * b }),
}

class Equation(descriptor: String) {
    val result: Long
    private val operands: List<Long>

    init {
        val parts = descriptor
            .split(":")
            .map { it.trim() }

        result = parts[0].toLong()
        operands = parts[1]
            .split(" ")
            .map { it.toLong() }
    }

    fun canCalibrate(): Boolean {
        val accumulator = operands[0]
        return checkBranch(accumulator, 0)
    }

    private fun checkBranch(accumulator: Long, i: Int): Boolean {
        if (i == operands.lastIndex - 1) {
            Operator.entries.forEach {
                val result = it.apply(accumulator, operands[i + 1])
                if (result == this.result) return true
            }

            return false
        }

        Operator.entries.forEach {
            val result = it.apply(accumulator, operands[i + 1])

            if (checkBranch(result, i + 1)) return true
        }

        return false
    }
}

fun parseEquations(): List<Equation> {
    val file = File("com/vadimtch/advent_of_code/year2024/challenge7/Challenge7.txt")

    return file
        .readLines()
        .filter { it.isNotBlank() }
        .map { Equation(it) }
}

fun main() {
    val equations = parseEquations()

    val sum = equations
        .filter { it.canCalibrate() }
        .sumOf { it.result }

    println(sum)
}
