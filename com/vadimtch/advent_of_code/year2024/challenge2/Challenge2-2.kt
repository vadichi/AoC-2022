package com.vadimtch.advent_of_code.year2024.challenge2

fun isDampenedReportSafe(report: List<Int>): Boolean {
    val safeRaw = isReportSafe(report)
    if (safeRaw.first) return true

    // Try removing the first of pair that failed
    val report1 = report.toMutableList().filterIndexed { index, _ -> index != safeRaw.second }
    if (isReportSafe(report1).first) return true

    // Try removing the second of pair that failed
    val report2 = report.toMutableList().filterIndexed { index, _ -> index != safeRaw.second + 1 }
    if (isReportSafe(report2).first) return true

    // Cannot resolve failure
    return false
}

fun main() {
    val reports = readReports()
    val safeCount = reports.count { isDampenedReportSafe(it) }

    println(safeCount)
}
