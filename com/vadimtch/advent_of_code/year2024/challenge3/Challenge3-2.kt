package com.vadimtch.advent_of_code.year2024.challenge3

sealed class Instruction {
    data object Enable : Instruction()
    data object Disable : Instruction()
    data class Mul(val a: Int, val b: Int) : Instruction()
}

fun matchInstructions(string: String): List<String> {
    val regex = Regex("mul\\(\\d+,\\d+\\)|do\\(\\)|don't\\(\\)")
    return regex.findAll(string).map { it.value }.toList()
}

fun parseInstructions(instruction: String): Instruction {
    return when(instruction) {
        "do()" -> Instruction.Enable
        "don't()" -> Instruction.Disable
        else -> {
            val (a, b) = instruction
                .removePrefix("mul")
                .removePrefix("(")
                .removeSuffix(")")
                .split(",")
                .map { it.toInt() }
            Instruction.Mul(a, b)
        }
    }
}

fun main() {
    val memory = readMemory()
    val instructions = matchInstructions(memory)

    var enabled = true
    var sum = 0
    for (instruction in instructions) {
        when(val parsedInstruction = parseInstructions(instruction)) {
            is Instruction.Enable -> enabled = true
            is Instruction.Disable -> enabled = false
            is Instruction.Mul -> {
                if (enabled) {
                    sum += parsedInstruction.a * parsedInstruction.b
                }
            }
        }
    }

    println(sum)
}
