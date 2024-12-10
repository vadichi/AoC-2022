package com.vadimtch.advent_of_code.year2024.challenge7

import java.io.File

// To make it easier to iterate over all possible combinations of operands,
// a list of operands can be described by a bit mask.
// The i-th operator is represented by the i-th lowest bit of the mask,
// where 0 corresponds to addition, and 1 corresponds to multiplication.
class OperandList(private val mask: Int) {
    fun evaluate(operands: List<Long>): Long {
        var accumulator = operands[0]
        for (i in 1 until  operands.size) {
            accumulator = apply(accumulator, operands[i], i - 1)
        }

        return accumulator
    }

    private fun apply(left: Long, right: Long, operandOffset: Int): Long {
        val result = when(operandAt(operandOffset)) {
            0 -> left + right
            1 -> left * right
            else -> throw IllegalStateException()
        }

        return result
    }

    private fun operandAt(offset: Int) = (mask shr offset) and 1
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
        var operandMask = 0
        while(operandMask < (1 shl (operands.size - 1))) {
            val operandList = OperandList(operandMask)
            if (operandList.evaluate(operands) == result) return true

            operandMask++
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
