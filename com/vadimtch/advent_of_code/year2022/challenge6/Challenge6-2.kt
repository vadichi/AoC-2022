package com.vadimtch.advent_of_code.year2022.challenge6

import java.io.File

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge6/Challenge6.txt")
    val data = file.readLines()[0]

    val length = 14

    for (i in length - 1 until data.length) {
            val set = data.toCharArray(i - (length - 1), i + 1).toHashSet()

            if (set.size == 14) {
                // Convert index to position
                println(i + 1)
                break
            }
    }
}