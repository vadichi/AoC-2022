package com.vadimtch.advent_of_code.year2023.challenge1

val DIGIT_SPELLINGS = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

fun decodeEdgeDigits(line: String): String {
    var result = line

    val first = line.findAnyOf(DIGIT_SPELLINGS.keys)
    if (first != null) {
        val index = first.first
        val word = first.second

        // Insert digit equivalent right before the word
        // Preserves any word overlaps at the end (a single letter can be used in several words)
        result = result.substring(0, index) + DIGIT_SPELLINGS[word] + result.substring(index)
    }

    val last = result.findLastAnyOf(DIGIT_SPELLINGS.keys)
    if (last != null) {
        val index = last.first
        val word = last.second

        // Insert digit equivalent right after the word
        // Preserves any word overlaps at the beginning
        result = result.substring(0, index + word.length) + DIGIT_SPELLINGS[word] + result.substring(index + word.length)
    }

    return result
}

fun main() {
    val lines = readLines()
    val sum = lines
        .map { line -> decodeEdgeDigits(line) }
        .sumOf { line -> calculateCalibrationNumber(line) }

    println(sum)
}
