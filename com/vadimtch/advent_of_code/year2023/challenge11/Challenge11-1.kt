package com.vadimtch.advent_of_code.year2023.challenge11

import java.io.File
import java.math.BigInteger

data class Galaxy(
    var x: BigInteger,
    var y: BigInteger,
) {
    fun manhattanDistance(other: Galaxy): BigInteger {
        return (x - other.x).abs() + (y - other.y).abs()
    }
}

class Image(
    private val expansionFactor: BigInteger,

    baseMapLines: List<String>,
) {

    private val baseMap: Array<CharArray>
    private val galaxies: MutableList<Galaxy> = mutableListOf()

    init {
        baseMap = Array(baseMapLines.size) { CharArray(baseMapLines[0].length) }
        baseMapLines.forEachIndexed { y, line ->
            baseMap[y] = line.toCharArray()
        }

        findGalaxies()
        expandGalaxies()
    }

    fun calculateDistanceSum(): BigInteger {
        return generateGalaxyPairs()
            .sumOf {
                it.elementAt(0).manhattanDistance(it.elementAt(1))
            }
    }

    private fun findGalaxies() {
        baseMap.forEachIndexed { y, line ->
            line.forEachIndexed { x, it ->
                if (it == '#') {
                    galaxies.add(Galaxy(x.toBigInteger(), y.toBigInteger()))
                }
            }
        }
    }

    private fun expandGalaxies() {
        val verticalBoundaries = findVerticalExpansionBoundaries()
        val horizontalBoundaries = findHorizontalExpansionBoundaries()

        galaxies.forEach {
            val horizontalExpansions = horizontalBoundaries.count { x -> x.toBigInteger() < it.x }
            val verticalExpansions = verticalBoundaries.count { y -> y.toBigInteger() < it.y }

            it.x += horizontalExpansions.toBigInteger() * expansionFactor
            it.y += verticalExpansions.toBigInteger() * expansionFactor
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

    private fun findVerticalExpansionBoundaries(): List<Int> {
        val boundaries = mutableListOf<Int>()
        baseMap.forEachIndexed { y, line ->
            if (line.all { it == '.' }) {
                boundaries.add(y)
            }
        }

        return boundaries
    }

    private fun findHorizontalExpansionBoundaries(): List<Int> {
        val boundaries = mutableListOf<Int>()
        baseMap[0].forEachIndexed { x, _ ->
            if (baseMap.all { it[x] == '.' }) {
                boundaries.add(x)
            }
        }

        return boundaries
    }

}

fun parseInput(): List<String> {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge11/Challenge11.txt")

    return file.readLines()
}

fun main() {
    val lines = parseInput()
    val image = Image(BigInteger.ONE, lines)

    println(image.calculateDistanceSum())
}
