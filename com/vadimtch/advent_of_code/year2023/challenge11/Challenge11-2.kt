package com.vadimtch.advent_of_code.year2023.challenge11

import java.io.File
import java.math.BigInteger

val EXPANSION_FACTOR = 1_000_000 - 1

data class BigGalaxy(
    var x: BigInteger,
    var y: BigInteger,
) {
    fun manhattanDistance(other: BigGalaxy): BigInteger {
        return (x - other.x).abs() + (y - other.y).abs()
    }
}

class BigImage(mapLines: List<String>) {
    private val baseMap: MutableList<MutableList<Char>> = mutableListOf()
    private val galaxies: MutableList<BigGalaxy> = mutableListOf()

    init {
        mapLines.forEach {
            baseMap.add(it.toMutableList())
        }

        scanGalaxies()
        expandGalaxies()
    }

    fun calculateDistanceSum(): BigInteger {
        val pairs = generateGalaxyPairs()
        return pairs.sumOf { it.elementAt(0).manhattanDistance(it.elementAt(1)) }
    }

    private fun scanGalaxies() {
        baseMap.forEachIndexed { y, line ->
            line.forEachIndexed { x, it ->
                if (it == '#') {
                    galaxies.add(BigGalaxy(x.toBigInteger(), y.toBigInteger()))
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

            it.x += horizontalExpansions.toBigInteger() * EXPANSION_FACTOR.toBigInteger()
            it.y += verticalExpansions.toBigInteger() * EXPANSION_FACTOR.toBigInteger()
        }
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

    private fun generateGalaxyPairs(): Set<Set<BigGalaxy>> {
        val pairs = mutableSetOf<Set<BigGalaxy>>()

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

fun parseInputNew(): BigImage {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge11/Challenge11.txt")
    val lines = file.readLines()

    return BigImage(lines)
}

fun main() {
    val image = parseInputNew()
    println(image.calculateDistanceSum())
}
