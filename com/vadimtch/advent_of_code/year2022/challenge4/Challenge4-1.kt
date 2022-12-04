package com.vadimtch.advent_of_code.year2022.challenge4

import java.io.File

fun readAssignments(line: String): List<Array<Int>> {
    val strings = line.split(",")
    val stringAssignments = arrayOf(strings[0].split("-"), strings[1].split("-"))

    return stringAssignments.map {
        arrayOf(Integer.parseInt(it[0]), Integer.parseInt(it[1]))
    }
}

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge4/Challenge4.txt")

    var overlappingAssignments = 0
    file.forEachLine { line ->
        val assignments = readAssignments(line)

        if ((assignments[0][0] >= assignments[1][0]) && (assignments[0][1] <= assignments[1][1])) {
            overlappingAssignments++
        } else if ((assignments[1][0] >= assignments[0][0]) && (assignments[1][1] <= assignments[0][1])) {
            overlappingAssignments++
        }
    }

    println(overlappingAssignments)
}