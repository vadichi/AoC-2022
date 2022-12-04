package com.vadimtch.advent_of_code.year2021.challenge3

import java.io.File

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2021/challenge3/Challenge3.txt")

    val bitCounts = ArrayList<Array<Int>>()
    file.forEachLine { line ->
        if (bitCounts.isEmpty()) {
            repeat(line.length) {
                bitCounts.add(arrayOf(0,0 ))
            }
        }

        var i = 0
        line.forEach {
            val bit = Integer.parseInt(it.toString())
            bitCounts[i][bit] += 1

            i++
        }
    }

    val gammaRateBits = Array(bitCounts.size) { 0 }
    val epsilonRateBits = Array(bitCounts.size) { 0 }


    var i = 0
    bitCounts.forEach {
        if (it[0] >= it[1]) {
            gammaRateBits[i] = 0
            epsilonRateBits[i] = 1
        } else {
            gammaRateBits[i] = 1
            epsilonRateBits[i] = 0
        }

        i++
    }

    var gammaRate = 0
    var epsilonRate = 0
    var coefficient = 1

    for (j in gammaRateBits.size - 1 downTo 0) {
        epsilonRate += epsilonRateBits[j] * coefficient
        gammaRate += gammaRateBits[j] * coefficient

        coefficient *= 2
    }

    print(epsilonRate * gammaRate)
}