package com.vadimtch.advent_of_code.year2023.challenge15

import java.io.File

class Step(
    private val string: String,
) {
    private val lensLabel: String
    private val operationCode: Char
    private val lensFocalLength: Int?

    init {
        val operationCodeIndex = string.indexOfFirst { it in setOf('-', '=') }

        lensLabel = string.substring(0, operationCodeIndex)
        operationCode = string[operationCodeIndex]

        lensFocalLength = if (operationCode == '-') {
            null
        } else {
            string.substring(operationCodeIndex + 1).toInt()
        }
    }

    fun getLensLabel(): String {
        return lensLabel
    }

    fun getBoxNumber(): Int {
        return lensLabel.calculateHASH()
    }

    fun getOperationCode(): Char {
        return operationCode
    }

    fun getLensFocalLength(): Int? {
        return lensFocalLength
    }

    fun calculateHash(): Int {
        return string.calculateHASH()
    }
}

class InitialisationSequence(descriptor: String) {
    private val steps: List<Step>

    init {
        val tokens = descriptor.split(",")
        steps = tokens.map { Step(it) }
    }

    fun calculateHashSum(): Int {
        return steps.sumOf { it.calculateHash() }
    }

    fun forEach(action: (Step) -> Unit) {
        steps.forEach(action)
    }
}

fun String.calculateHASH(): Int {
    var hash = 0
    this.forEach {
        hash += it.code

        hash *= 17
        hash %= 256
    }

    return hash
}

fun parseInput(): InitialisationSequence {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge15/Challenge15.txt")
    val descriptor = file
        .readText()
        .replace("\n", "")

    return InitialisationSequence(descriptor)
}

fun main() {
    val sequence = parseInput()
    println(sequence.calculateHashSum())
}
