package com.vadimtch.advent_of_code.year2021.challenge3

import java.io.File

fun main() {
    val lines = File("com/vadimtch/advent_of_code/year2021/challenge3/Challenge3.txt").readLines()
    var bitCounts = countBits(lines)

    var mostCommonBits = findMostCommonBits(bitCounts)
    var leastCommonBits = findLeastCommonBits(bitCounts)

    val mostCommonFittingLines = ArrayList(lines)
    val leastCommonFittingLines = ArrayList(lines)

    for (i in mostCommonBits.indices) {
        mostCommonFittingLines.removeIf { line ->
            line[i] != mostCommonBits[i]
        }

        if (mostCommonFittingLines.size == 1) {
            break
        }

        bitCounts = countBits(mostCommonFittingLines)
        mostCommonBits = findMostCommonBits(bitCounts)
    }

    for (i in leastCommonBits.indices) {
        leastCommonFittingLines.removeIf { line ->
            line[i] != leastCommonBits[i]
        }

        if (leastCommonFittingLines.size == 1) {
            break
        }

        bitCounts = countBits(leastCommonFittingLines)
        leastCommonBits = findLeastCommonBits(bitCounts)
    }

    val oxygenBits = mostCommonFittingLines[0]
    val carbonDioxideBits = leastCommonFittingLines[0]

    val oxygenRating = bitsToDecimal(oxygenBits.toCharArray().toTypedArray())
    val carbonDioxideRating = bitsToDecimal(carbonDioxideBits.toCharArray().toTypedArray())

    println(oxygenRating * carbonDioxideRating)
}