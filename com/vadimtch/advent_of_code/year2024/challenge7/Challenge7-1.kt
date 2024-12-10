package com.vadimtch.advent_of_code.year2024.challenge7

import java.io.File

typealias Operator = ((Long, Long) -> Long)

class Equation(descriptor: String, private val operators: List<Operator>) {
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
        if (i == operands.lastIndex) {
            return accumulator == result
        }

        operators.forEach {
            val result = it.invoke(accumulator, operands[i + 1])

            if (checkBranch(result, i + 1)) return true
        }

        return false
    }
}

fun parseEquations(operators: List<Operator>): List<Equation> {
    val file = File("com/vadimtch/advent_of_code/year2024/challenge7/Challenge7.txt")

    return file
        .readLines()
        .filter { it.isNotBlank() }
        .map {
            Equation(
                it,
                operators
            )
        }
}

fun main() {
    val equations = parseEquations(
        listOf(
            { a, b -> a + b },
            { a, b -> a * b }
        )
    )

    val sum = equations
        .filter { it.canCalibrate() }
        .sumOf { it.result }

    println(sum)
}
