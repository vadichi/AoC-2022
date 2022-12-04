package com.vadimtch.advent_of_code.year2021.challenge1

import java.io.File

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2021/challenge1/Challenge1.txt")

    var lastDepth = 0
    var increasingDepthsCount = 0
    file.forEachLine {
        val depth = Integer.parseInt(it)
        if (depth > lastDepth) {
            increasingDepthsCount++
        }

        lastDepth = depth
    }

    // Do not count the first increase (no initial depth)
    increasingDepthsCount--

    println(increasingDepthsCount)
}