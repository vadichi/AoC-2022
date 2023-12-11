package com.vadimtch.advent_of_code.year2023.challenge11

import java.io.File
import kotlin.math.abs

data class Galaxy(
    val x: Int,
    val y: Int,
) {
    fun manhattanDistance(other: Galaxy): Int {
        return abs(x - other.x) + abs(y - other.y)
    }
}

class Image(mapLines: List<String>) {
    private val map: MutableList<MutableList<Char>> = mutableListOf()
    private val galaxies: MutableList<Galaxy> = mutableListOf()

    init {
        mapLines.forEach {
            map.add(it.toMutableList())
        }

        expand()
        scanGalaxies()
    }

    fun calculateDistanceSum(): Int {
        val pairs = generateGalaxyPairs()

        return pairs.sumOf { it.elementAt(0).manhattanDistance(it.elementAt(1)) }
    }

    private fun expand() {
        var y = 0
        while (y <= map.lastIndex) {
            if (map[y].all { it == '.' }) {
                map.add(y + 1, MutableList(map[0].size) { '.' })
                y += 1
            }

            y += 1
        }

        var x = 0
        while (x <= map[0].lastIndex) {
            if (map.all { it[x] == '.' }) {
                map.forEach { it.add(x + 1, '.') }
                x += 1
            }

            x += 1
        }
    }

    private fun scanGalaxies() {
        map.forEachIndexed { y, line ->
            line.forEachIndexed { x, it ->
                if (it == '#') {
                    galaxies.add(Galaxy(x, y))
                }
            }
        }
    }

    private fun generateGalaxyPairs(): Set<Set<Galaxy>> {
        val pairs = mutableSetOf<Set<Galaxy>>()

        galaxies.forEach { galaxy1 ->
            galaxies.forEach { galaxy2 ->
                if (galaxy1 != galaxy2) {
                    pairs.add(setOf(galaxy1, galaxy2))
                }
            }
        }

        return pairs
    }
}

fun parseInput(): Image {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge11/Challenge11.txt")
    val lines = file.readLines()

    return Image(lines)
}

fun main() {
    val image = parseInput()
    println(image.calculateDistanceSum())
}
