package com.vadimtch.advent_of_code.year2023.challenge11

fun main() {
    val expansionFactor = 1_000_000 - 1

    val lines = parseInput()
    val image = Image(expansionFactor.toBigInteger(), lines)
    println(image.calculateDistanceSum())
}
