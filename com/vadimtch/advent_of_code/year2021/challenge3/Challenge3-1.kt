package com.vadimtch.advent_of_code.year2021.challenge3

import java.io.File

fun countBits(lines: List<String>): Array<Array<Int>> {
    val bitCounts = Array(lines[0].length) { arrayOf(0, 0) }

    lines.forEach { line ->

        for ((i, char) in line.withIndex()) {
            when(char) {
                '0' -> bitCounts[i][0] += 1
                '1' -> bitCounts[i][1] += 1
            }
        }
    }

    return bitCounts
}

fun findMostCommonBits(bitCounts: Array<Array<Int>>): Array<Char> {
    val mostCommonBits = Array(bitCounts.size) { '0' }

    for ((i, bits) in bitCounts.withIndex()) {
        if (bits[0] > bits[1]) {
            mostCommonBits[i] = '0'
        } else {
            mostCommonBits[i] = '1'
        }
    }

    return mostCommonBits
}

fun findLeastCommonBits(bitCounts: Array<Array<Int>>): Array<Char> {
    val leastCommonBits = Array(bitCounts.size) { '0' }

    for ((i, bits) in bitCounts.withIndex()) {
        if (bits[0] > bits[1]) {
            leastCommonBits[i] = '1'
        } else {
            leastCommonBits[i] = '0'
        }
    }

    return leastCommonBits
}

fun bitsToDecimal(bits: Array<Char>): Int {
    var decimal = 0
    var coefficient = 1

    for (i in bits.size - 1 downTo 0) {
        decimal += Integer.parseInt(bits[i].toString()) * coefficient
        coefficient *= 2
    }

    return decimal
}

fun main() {
    val lines = File("com/vadimtch/advent_of_code/year2021/challenge3/Challenge3.txt").readLines()
    val bitCounts = countBits(lines)

    val mostCommonBits = findMostCommonBits(bitCounts)
    val leastCommonBits = findLeastCommonBits(bitCounts)

    val gammaRate = bitsToDecimal(mostCommonBits)
    val epsilonRate = bitsToDecimal(leastCommonBits)

    println(gammaRate * epsilonRate)
}