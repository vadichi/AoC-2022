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

fun isReportSafe(report: List<Int>): Boolean {
    val direction = (report[1] - report[0]).sign

    for (i in 0..<report.size - 1) {
        val delta = report[i + 1] - report[i]

        var reject = false
        if (direction != delta.sign) return false
        if (delta.absoluteValue < 1) return false
        if (delta.absoluteValue > 3) return false
    }

    return true
}

fun main() {
    val reports = readReports()
    val safeCount = reports.count { isReportSafe(it) }

    println(safeCount)
}
