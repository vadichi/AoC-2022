package com.vadimtch.advent_of_code.year2022.challenge5

import java.io.BufferedReader
import java.io.File

typealias ContainerState = Array<ArrayDeque<Char>>

// Leaves the reader at the first line of instructions
fun readInitialStateLines(reader: BufferedReader): ArrayList<String> {
    val initialStateLines = ArrayList<String>()
    var line = reader.readLine()

    do {
        initialStateLines.add(line)
        line = reader.readLine()
    } while (line.isNotEmpty())

    return initialStateLines
}

fun parseInitialState(initialStateLines: ArrayList<String>): ContainerState {
    // The crate IDs in the input are located at positions 1, 5, 9, 13, ...
    // These positions are modeled as 4k + 1 | for integer k >= 0

    // Line with column numbers is the penultimate line
    val size = initialStateLines[initialStateLines.size - 2].split(" ").size
    val initialState = Array(size) { ArrayDeque<Char>()}

    initialStateLines.asReversed().removeAt(0)

    var position = 1
    for (i in 0 until size) {
        for (line in initialStateLines.asReversed()) {
            val char = try {line[position]} catch (e: StringIndexOutOfBoundsException) { break }

            if (char.isWhitespace()) {
                break
            }

            initialState[i].addLast(char)
        }

        position += 4
    }

    return initialState
}

fun processInstruction(instruction: String, state: ContainerState) {
    val tokens = instruction.split(" ")

    val repeats = Integer.parseInt(tokens[1])
    val origin = Integer.parseInt(tokens[3]) - 1
    val destination = Integer.parseInt(tokens[5]) - 1

    repeat(repeats) {
        val element = state[origin].removeLast()
        state[destination].addLast(element)
    }
}

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge5/Challenge5.txt")
    val reader = file.bufferedReader()

    val initialStateLines = readInitialStateLines(reader)
    val containerState = parseInitialState(initialStateLines)

    reader.forEachLine { line ->
        processInstruction(line, containerState)
    }

    val answer = CharArray(containerState.size)
    for ((i, column) in containerState.withIndex()) {
        answer[i] = column.removeLast()
    }

    println(String(answer))
}