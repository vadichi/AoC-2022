package com.vadimtch.advent_of_code.year2023.challenge3

fun main() {
    val schematic = parseSchematic()

    val sum = schematic.getGearRatios()
        .values
        .reduce(Int::plus)

    println(sum)
}
