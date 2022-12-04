package com.vadimtch.advent_of_code.year2022.challenge4

import java.io.File

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge4/Challenge4.txt")

    var overlappingAssignments = 0
    file.forEachLine { line ->
        val assignments = readAssignments(line)

        if (assignments[0][0] in assignments[1][0]..assignments[1][1]) {
            overlappingAssignments++
        } else if (assignments[0][1] in assignments[1][0]..assignments[1][1]) {
            overlappingAssignments++
        } else if (assignments[1][0] in assignments[0][0]..assignments[0][1]) {
            overlappingAssignments++
        } else if (assignments[1][1] in assignments[0][0]..assignments[0][1]) {
            overlappingAssignments++
        }
    }

    println(overlappingAssignments)
}