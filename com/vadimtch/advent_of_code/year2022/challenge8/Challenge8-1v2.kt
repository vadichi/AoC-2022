package com.vadimtch.advent_of_code.year2022.challenge8

import java.io.File
import java.util.EnumMap

class InvertedArray<T> (private val array: Array<Array<T>>) {
    operator fun get(x: Int, y: Int): T {
        return array[y][x]
    }
}

fun <T> Array<Array<T>>.asInverted(): InvertedArray<T> {
    return InvertedArray(this)
}

operator fun <T> Array<Array<T>>.get(x: Int, y: Int): T {
    return this[x][y]
}

enum class Direction {
    Right,
    Left,
    Up,
    Down
}

class Cell(
    var height: Int = 0,
    val treeline: EnumMap<Direction, Int> = EnumMap(hashMapOf(
        Pair(Direction.Right, -1),
        Pair(Direction.Left, -1),
        Pair(Direction.Up, -1),
        Pair(Direction.Down, -1)
    ))
) {
    fun isVisible(): Boolean {
        return (height > treeline[Direction.Right]!!) ||
                (height > treeline[Direction.Left]!!) ||
                (height > treeline[Direction.Up]!!) ||
                (height > treeline[Direction.Down]!!)
    }
}

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge8/Challenge8.txt")
    val lines = file.readLines()

    val xSize = lines[0].length
    val ySize = lines.size
    val map = Array(xSize) { Array(ySize) { Cell() } }

    for (x in 0 until xSize) {
        for (y in 0 until ySize) {
            map[x][y] = Cell(lines[y][x].digitToInt())
        }
    }

    // map[x, y]
    // map.asReversed[x, y]
}
