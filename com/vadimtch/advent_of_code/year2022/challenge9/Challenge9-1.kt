package com.vadimtch.advent_of_code.year2022.challenge9

import java.io.File

data class Position(var x: Int, var y: Int)

class Rope(length: Int) {
    private val nodes = Array(length) { Position(0, 0) }
    private val tailPositions = hashSetOf(nodes.last().copy())

    fun getPositionsVisitedCount(): Int {
        return tailPositions.size
    }

    fun parseInstruction(instruction: String) {
        val tokens = instruction.split(" ")
        val command = tokens[0]
        val steps = tokens[1].toInt()

        repeat(steps) {
            when (command) {
                "R" -> nodes[0].x++
                "L" -> nodes[0].x--
                "U" -> nodes[0].y++
                "D" -> nodes[0].y--
            }

            moveNodes()
            tailPositions.add(nodes.last().copy())
        }
    }


    private fun moveNodes() {
        for (i in 1 until nodes.size) {
            val xDistance = nodes[i - 1].x - nodes[i].x
            val yDistance = nodes[i - 1].y - nodes[i].y

            if (xDistance !in -1..1) {
                nodes[i].x += stepInDirection(xDistance)

                // Diagonally separated
                if (yDistance != 0) {
                    nodes[i].y += stepInDirection(yDistance)
                }
            }

            if (yDistance !in -1..1) {
                nodes[i].y += stepInDirection(yDistance)

                // Diagonally separated
                if (xDistance != 0) {
                    nodes[i].x += stepInDirection(xDistance)
                }
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
    val rope = Rope(2)

    file.forEachLine { line ->
        rope.parseInstruction(line)
    }

    println(rope.getPositionsVisitedCount())
}
