package com.vadimtch.advent_of_code.year2022.challenge6

import java.io.File

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge6/Challenge6.txt")
    val data = file.readLines()[0]

    for (i in 3 until data.length) {
        val set = setOf(data[i], data[i - 1], data[i - 2], data[i - 3])

        if (set.size == 4) {
            // Position in the data rather than index
            println(i + 1)
            break
        }
    }
}