package com.vadimtch.advent_of_code.year2024.challenge4

fun main() {
    val matrix = parseMatrix()

    // Only diagonal matches can form X shapes.
    val diagonalMatches = findAllMatches(matrix, "MAS")
        .filter { it.direction in setOf(Direction.NE, Direction.NW, Direction.SE, Direction.SW) }

    // Middle letter (index = 1) of paired matches must be at the same location.
    // The same applies to any word of odd length. Even length words can't really form X shapes.
    val pairedMatches = diagonalMatches
        .groupBy { Pair(it.x + it.direction.xOffset, it.y + it.direction.yOffset) }
        .filter { it.value.size == 2 }

    println(pairedMatches.size)
}
