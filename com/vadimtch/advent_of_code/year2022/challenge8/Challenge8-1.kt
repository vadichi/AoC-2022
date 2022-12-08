package com.vadimtch.advent_of_code.year2022.challenge8

import java.io.File
import kotlin.math.max

enum class Direction {
    Right,
    Left,
    Up,
    Down
}

class Cell(
    var height: Int = 0,
    var treelineRight: Int = -1,
    var treelineLeft: Int = -1,
    var treelineUp: Int = -1,
    var treelineDown: Int = -1
) {
    fun isVisible(): Boolean {
        return (height > treelineRight) || (height > treelineLeft) || (height > treelineUp) || (height > treelineDown)
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

    for (x in 0 until xSize) {
        for (y in 1 until ySize) {
            val previousTreeline = map[x][y - 1].treelineDown
            val previousHeight = map[x][y - 1].height

            map[x][y].treelineDown = max(previousTreeline, previousHeight)
        }
    }

    for (x in 0 until xSize) {
        for (y in ySize - 2 downTo 0) {
            val previousTreeline = map[x][y + 1].treelineUp
            val previousHeight = map[x][y + 1].height

            map[x][y].treelineUp = max(previousTreeline, previousHeight)
        }
    }

    for (y in 0 until ySize) {
        for (x in 1 until xSize) {
            val previousTreeline = map[x - 1][y].treelineRight
            val previousHeight = map[x - 1][y].height

            map[x][y].treelineRight = max(previousTreeline, previousHeight)
        }
    }

    for (y in 0 until ySize) {
        for (x in xSize - 2 downTo 0) {
            val previousTreeline = map[x + 1][y].treelineLeft
            val previousHeight = map[x + 1][y].height

            map[x][y].treelineLeft = max(previousTreeline, previousHeight)
        }
    }

    var visible = 0
    map.forEach { yArray ->
        yArray.forEach { cell ->
            if (cell.isVisible()) {
                visible++
            }
        }
    }

    println(visible)
}