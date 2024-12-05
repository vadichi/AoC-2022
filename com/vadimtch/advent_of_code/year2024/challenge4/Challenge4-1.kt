package com.vadimtch.advent_of_code.year2024.challenge4

import java.io.File

// List of lines, where each is a list of characters. Hence, indexed as matrix[y][x].
typealias Matrix = List<List<Char>>

const val TARGET = "XMAS"

const val DEBUG = false

enum class Direction(val xOffset: Int, val yOffset: Int) {
    N(0, -1),
    S(0, 1),
    E(1, 0),
    W(-1, 0),

    NE(1, -1),
    NW(-1, -1),
    SE(1, 1),
    SW(-1, 1);
}

fun parseMatrix(): Matrix {
    val file = File("com/vadimtch/advent_of_code/year2024/challenge4/Challenge4.txt")

    return file.readLines().map { line -> line.toList() }
}

fun search(matrix: Matrix, index: Int, x: Int, y: Int, direction: Direction): Boolean {
    if (matrix[y][x] != TARGET[index]) return false

    val nextIndex = index + 1
    if (nextIndex == TARGET.length) return true

    val nextX = x + direction.xOffset
    val nextY = y + direction.yOffset

    if (nextX < 0 || nextX >= matrix[0].size || nextY < 0 || nextY >= matrix.size) return false

    return search(matrix, nextIndex, nextX, nextY, direction)
}

fun main() {
    val matrix = parseMatrix()

    var count = 0
    for (y in matrix.indices) {
        var rowCounter = 0

        for (x in matrix[y].indices) {
            // Consider only possible words originating at the location.
            // Avoids double counting, as all the letters unique => each word has a single starting point.
            if (matrix[y][x] == TARGET[0]) {
                for (direction in Direction.entries) {
                    if (search(matrix, 0, x, y, direction)) {
                        count++

                        if (DEBUG) {
                            println("Found word $TARGET at ($x, $y) in direction $direction")
                            rowCounter++
                        }
                    }
                }
            }
        }

        if (DEBUG) {
            println("Row $y total: $rowCounter")
        }
    }

    println(count)
}
