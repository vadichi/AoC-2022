package com.vadimtch.advent_of_code.year2023.challenge10

import java.io.File

enum class Direction {
    NORTH, EAST, SOUTH, WEST;

    fun inverse(): Direction {
        return when (this) {
            NORTH -> SOUTH
            EAST -> WEST
            SOUTH -> NORTH
            WEST -> EAST
        }
    }

    fun move(startLocation: Pair<Int, Int>): Pair<Int, Int> {
        return when (this) {
            NORTH -> Pair(startLocation.first, startLocation.second - 1)
            EAST -> Pair(startLocation.first + 1, startLocation.second)
            SOUTH -> Pair(startLocation.first, startLocation.second + 1)
            WEST -> Pair(startLocation.first - 1, startLocation.second)
        }
    }
}

val pipeDirections = mapOf(
    '|' to setOf(Direction.NORTH, Direction.SOUTH),
    '-' to setOf(Direction.EAST, Direction.WEST),
    'L' to setOf(Direction.NORTH, Direction.EAST),
    'J' to setOf(Direction.NORTH, Direction.WEST),
    'F' to setOf(Direction.SOUTH, Direction.EAST),
    '7' to setOf(Direction.SOUTH, Direction.WEST),
    '.' to setOf()
)

data class Move(val direction: Direction, val startLocation: Pair<Int, Int>)

class Maze(mapLines: List<String>) {
    private val map: Array<CharArray>

    private var pathRecord: MutableList<Move>
    private var currentLocation: Pair<Int, Int>

    init {
        map = Array(mapLines.size) { CharArray(mapLines[0].length) }
        mapLines.forEachIndexed { y, line ->
            map[y] = line.toCharArray()
        }

        pathRecord = mutableListOf()
        currentLocation = findStartLocation()
    }

    fun findHalfwayDistance(): Int {
        stepThroughMaze()
        return pathRecord.size / 2
    }

    private fun stepThroughMaze() {
        do {
            step()
        } while (map[currentLocation.second][currentLocation.first] != 'S')
    }

    private fun step() {
        val directionChoice = if (pathRecord.isEmpty()) {
            findStartLocationDirections().first()
        } else {
            val currentPipe = map[currentLocation.second][currentLocation.first]
            val possibleDirections = pipeDirections[currentPipe]!!
            possibleDirections.first { it != pathRecord.last().direction.inverse() }
        }

        // This assumes that any pipe connection is a valid move, i.e.
        // does not lead off the map and connected to another pipe in a valid way
        pathRecord.add(Move(directionChoice, currentLocation))
        currentLocation = directionChoice.move(currentLocation)
    }

    private fun findStartLocation(): Pair<Int, Int> {
        map.forEachIndexed { y, line ->
            line.forEachIndexed { x, it ->
                if (it == 'S') {
                    return Pair(x, y)
                }
            }
        }
        throw IllegalStateException("No start location found")
    }

    private fun findStartLocationDirections(): Set<Direction> {
        return Direction.entries.filter { direction ->
            val destination = direction.move(currentLocation)

            val destinationPipe = map
                .getOrElse(destination.second) { return@filter false }
                .getOrElse(destination.first) { return@filter false }

            val destinationDirections = pipeDirections[destinationPipe]!!
            destinationDirections.contains(direction.inverse())
        }.toSet()
    }
}

fun parseInput(): Maze {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge10/Challenge10.txt")
    val lines = file.readLines()

    return Maze(lines)
}

fun main() {
    val maze = parseInput()
    val result = maze.findHalfwayDistance()

    println(result)
}
