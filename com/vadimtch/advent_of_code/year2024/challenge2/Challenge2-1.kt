package com.vadimtch.advent_of_code.year2024.challenge2

import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.sign

fun readReports(): List<List<Int>> {
    val file = File("com/vadimtch/advent_of_code/year2024/challenge2/Challenge2.txt")

    return file.readLines().map { line ->
        line.split(" ").map { it.toInt() }
    }
}

fun isReportSafe(report: List<Int>): Pair<Boolean, Int> {
    // A majority vote for the direction based on the first 3 pairs.
    val direction = (0..2)
        .map { i -> (report[i + 1] - report[i]).sign }
        .groupBy { it }
        .maxBy { it.value.size }
        .key

    for (i in 0..<report.size - 1) {
        val delta = report[i + 1] - report[i]

        if (direction != delta.sign) return Pair(false, i)
        if (delta.absoluteValue < 1) return Pair(false, i)
        if (delta.absoluteValue > 3) return Pair(false, i)
    }

    return Pair(true, 0)
}

fun main() {
    val reports = readReports()
    val safeCount = reports.count { isReportSafe(it).first }

    println(safeCount)
}
