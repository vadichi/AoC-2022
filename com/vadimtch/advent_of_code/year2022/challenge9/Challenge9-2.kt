package com.vadimtch.advent_of_code.year2022.challenge9

import java.io.File

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge9/Challenge9.txt")
    val rope = Rope(10)

    file.forEachLine { line ->
        rope.parseInstruction(line)
    }

    println(rope.getPositionsVisitedCount())
}

