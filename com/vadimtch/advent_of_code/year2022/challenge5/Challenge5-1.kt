package com.vadimtch.advent_of_code.year2022.challenge5

import java.io.BufferedReader
import java.io.File

class Instruction(val count: Int, val origin: Int, val destination: Int)

open class ContainerShip(private val reader: BufferedReader) {
    protected lateinit var state: Array<ArrayDeque<Char>>

    init {
        getInitialState()
        executeInstructions()
    }

    fun getTopLayer(): String {
        val topLayer = CharArray(state.size)
        for ((i, column) in state.withIndex()) {
            topLayer[i] = column.removeLast()
        }

        return String(topLayer)
    }

    protected open fun swap(instruction: Instruction) {
        repeat(instruction.count) {
            val element = state[instruction.origin].removeLast()
            state[instruction.destination].addLast(element)
        }
    }

    private fun getInitialState() {
        val initialStateLines = ArrayList<String>()

        var line = reader.readLine()
        do {
            initialStateLines.add(line)
            line = reader.readLine()
        } while(line.isNotEmpty())

        val columnTitlesLine = initialStateLines[initialStateLines.size - 1]

        val columnTitles = ArrayList(columnTitlesLine.split(" "))
        columnTitles.removeIf { it.isBlank() }
        val columnCount = columnTitles.size

        initialStateLines.remove(columnTitlesLine)

        state = Array(columnCount) { ArrayDeque() }

        // Crate IDs are 4 characters apart starting from index 1
        var lineIndex = 1
        for (i in 0 until columnCount) {
            for (initialStateLine in initialStateLines.asReversed()) {
                // Index out of bounds => line ended
                val char = try { initialStateLine[lineIndex] } catch (_: StringIndexOutOfBoundsException) { break }

                if (char.isWhitespace()) {
                    // End of column
                    break
                }

                state[i].addLast(char)
            }

            lineIndex += 4
        }
    }

    private fun executeInstructions() {
        reader.forEachLine {line ->
            val instruction = parseInstruction(line)
            swap(instruction)
        }
    }

    private fun parseInstruction(instruction: String): Instruction {
        val tokens = instruction.split(" ")

        val count = Integer.parseInt(tokens[1])
        val origin = Integer.parseInt(tokens[3]) - 1
        val destination = Integer.parseInt(tokens[5]) - 1

        return Instruction(count, origin, destination)
    }
}

fun main() {
    val reader = File("com/vadimtch/advent_of_code/year2022/challenge5/Challenge5.txt").bufferedReader()
    val containerShip = ContainerShip(reader)

    println(containerShip.getTopLayer())
}