package com.vadimtch.advent_of_code.year2024.challenge3

import java.io.File

fun readMemory(): String {
    return File("com/vadimtch/advent_of_code/year2024/challenge3/Challenge3.txt").readText()
}

fun matchMulInstructions(string: String): List<String> {
    val regex = Regex("mul\\(\\d+,\\d+\\)")

    return regex.findAll(string).map { it.value }.toList()
}

fun parseMulInstruction(instruction: String): Pair<Int, Int> {
    return instruction
        .removePrefix("mul")
        .removePrefix("(")
        .removeSuffix(")")
        .split(",")
        .map { it.toInt() }
        .let { Pair(it[0], it[1]) }
}

fun main() {

    val memory = readMemory()
    val instructions = matchMulInstructions(memory)

    val sum = instructions
        .map { parseMulInstruction(it) }
        .fold(0) { sum, (a, b) -> sum + a * b }

    println(sum)
}
