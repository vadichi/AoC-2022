package com.vadimtch.advent_of_code.year2024.challenge6

import java.io.File

data class Position(var x: Int, var y: Int)

enum class Direction(val deltaX: Int, val deltaY: Int) {
    Up(0, -1),
    Right(1, 0),
    Down(0, 1),
    Left(-1, 0);

    fun next() =
        when(this) {
            Up -> Right
            Right -> Down
            Down -> Left
            Left -> Up
        }
}

class Grid(private val map: List<List<Char>>) {
    fun at(position: Position) = map[position.y][position.x]

    fun isInBounds(position: Position) =
        position.y in map.indices && position.x in map[0].indices

    fun findGuard(): Position {
        for (y in map.indices) {
            for (x in map[y].indices) {
                val position = Position(x, y)

                if (at(position) == '^') {
                    return position
                }
            }
        }

        throw IllegalStateException()
    }
}

class Guard(private val grid: Grid) {
    var position: Position = grid.findGuard()
    var direction: Direction = Direction.Up

    fun next(extraObstacle: Position? = null): Position {
        var next = ahead()
        if (!grid.isInBounds(next)) throw IllegalStateException()

        if (grid.at(next) == '#' || next == extraObstacle) {
            direction = direction.next()

            next = ahead()
            if (!grid.isInBounds(next)) throw IllegalStateException()
        }

        return next
    }

    private fun ahead() =
        Position(position.x + direction.deltaX, position.y + direction.deltaY)

    fun clone(): Guard {
        val guard = Guard(grid)
        guard.position = position
        guard.direction = direction

        return guard
    }
}

fun parseGrid(): Grid {
    val file = File("com/vadimtch/advent_of_code/year2024/challenge6/Challenge6.txt")

    return file
        .readLines()
        .map { line -> line.toList() }
        .let { Grid(it) }
}

fun main() {
    val grid = parseGrid()
    val guard = Guard(grid)

    val visited = mutableSetOf(guard.position)
    while(true) {
        val next = try {
            guard.next()
        } catch (e: IllegalStateException) {
            break
        }

        visited.add(next)
        guard.position = next
    }

    println(visited.size)
}

