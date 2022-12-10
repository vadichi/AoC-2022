package com.vadimtch.advent_of_code.year2022.challenge9

import java.io.File

data class Position(var x: Int, var y: Int)

class Rope {
    private var headPosition = Position(0, 0)
    private var tailPosition = Position(0, 0)
    private val positionsVisited = hashSetOf(tailPosition.copy())

    fun getPositionsVisitedCount(): Int {
        return positionsVisited.size
    }

    fun parseInstruction(instruction: String) {
        val tokens = instruction.split(" ")
        val command = tokens[0]
        val steps = tokens[1].toInt()

        repeat(steps) {
            when (command) {
                "R" -> headPosition.x++
                "L" -> headPosition.x--
                "U" -> headPosition.y++
                "D" -> headPosition.y--
            }

            moveTail()
            positionsVisited.add(tailPosition.copy())
        }
    }


    private fun moveTail() {
        val xDistance = headPosition.x - tailPosition.x
        val yDistance = headPosition.y - tailPosition.y

        if (xDistance !in -1..1) {
            tailPosition.x += stepInDirection(xDistance)

            // Diagonally separated
            if (yDistance != 0) {
                tailPosition.y += stepInDirection(yDistance)
            }
        }

        if (yDistance !in -1..1) {
            tailPosition.y += stepInDirection(yDistance)

            // Diagonally separated
            if (xDistance != 0) {
                tailPosition.x += stepInDirection(xDistance)
            }
        }
    }

    /**
     * Converts a distance to a step of -1, 0, or 1, preserving its direction
     */
    private fun stepInDirection(distance: Int): Int {
        return if (distance > 0) {
            1
        } else if (distance < 0) {
            -1
        } else {
            0
        }
    }
}

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge9/Challenge9.txt")
    val rope = Rope()

    file.forEachLine { line ->
        rope.parseInstruction(line)
    }

    println(rope.getPositionsVisitedCount())
}
