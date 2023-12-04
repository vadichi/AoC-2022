package com.vadimtch.advent_of_code.year2023.challenge1

import java.io.File

fun readLines(): List<String> {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge1/Challenge1.txt")
    return file.readLines()
}

fun findFirstDigit(line: String): Int {
    val firstChar = line.find { char ->
        char.isDigit()
    } ?: throw IllegalArgumentException("No digits found")

    return firstChar.digitToInt()
}

fun findLastDigit(line: String): Int {
    val lastChar = line.findLast { char ->
        char.isDigit()
    } ?: throw IllegalArgumentException("No digits found")

    return lastChar.digitToInt()
}

fun calculateCalibrationNumber(line: String): Int {
    val firstDigit = findFirstDigit(line)
    val lastDigit = findLastDigit(line)

    return firstDigit * 10 + lastDigit
}

fun main() {
    val lines = readLines()

    val sum = lines.sumOf { line ->
        calculateCalibrationNumber(line)
    }

    println(sum)
}
