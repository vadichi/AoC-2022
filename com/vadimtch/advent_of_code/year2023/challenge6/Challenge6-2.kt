package com.vadimtch.advent_of_code.year2023.challenge6

import java.io.File

fun loadLongRace(): Race {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge6/Challenge6.txt")

    val data = file.readLines()
        .asSequence()
        .map { it.split(":") }
        .map { it[1] }
        .map { it.replace(" ", "") }
        .map { it.toDouble() }
        .toList()

    val duration = data[0]
    val record = data[1]

    return Race(record, duration)
}

fun main() {
    val race = loadLongRace()
    val successfulHoldTimes = race.countSuccessfulHoldTimes()

    println(successfulHoldTimes)
}
