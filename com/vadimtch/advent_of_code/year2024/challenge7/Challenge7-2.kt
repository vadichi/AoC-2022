package com.vadimtch.advent_of_code.year2024.challenge7

import kotlin.math.pow

fun main() {
    val equations = parseEquations(
        listOf(
            { a, b -> a + b },
            { a, b -> a * b },
            { a, b ->
                val bDigits = kotlin.math.log10(b.toDouble()).toInt() + 1
                (a * 10.0.pow(bDigits) + b).toLong()
            }
        )
    )

    val sum = equations
        .filter { it.canCalibrate() }
        .sumOf { it.result }

    println(sum)
}


