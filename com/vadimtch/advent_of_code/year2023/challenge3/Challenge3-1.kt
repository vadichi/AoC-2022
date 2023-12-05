package com.vadimtch.advent_of_code.year2023.challenge3

import java.io.File

class Schematic(lines: List<String>) {

    private val array: Array<CharArray>
    private val adjacencyMapCache: Array<Array<Map<Pair<Int, Int>, Char>>>

    private lateinit var partNumbers: List<Int>
    private lateinit var gearRatios: Map<Pair<Int, Int>, Int>

    init {
        val sizeX = lines.first().length
        val sizeY = lines.size

        array = Array(sizeY) { CharArray(sizeX) }
        adjacencyMapCache = Array(sizeY) { Array(sizeX) { mutableMapOf() } }

        lines.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                array[y][x] = char
            }
        }

        scanGrid()
    }

    fun getPartNumbers(): List<Int> {
        return partNumbers
    }

    fun getGearRatios(): Map<Pair<Int, Int>, Int> {
        return gearRatios
    }

    private fun scanGrid(){
        var currentNumber = 0
        var currentNumberValid = false
        val currentNumberGears = mutableSetOf<Pair<Int, Int>>()

        val partNumbers = mutableListOf<Int>()
        val gearCoefficients = mutableMapOf<Pair<Int, Int>, MutableList<Int>>()

        fun startNewNumber() {
            if (currentNumberValid) {
                partNumbers.add(currentNumber)
            }

            currentNumberGears.forEach { gear ->
                gearCoefficients.getOrPut(gear) { mutableListOf() }.add(currentNumber)
            }

            currentNumber = 0
            currentNumberValid = false
            currentNumberGears.clear()
        }

        fun appendDigit(digit: Int) {
            currentNumber *= 10
            currentNumber += digit
        }

        array.forEachIndexed { y, line ->
            startNewNumber()

            line.forEachIndexed { x, char ->
                if (char.isDigit()) {
                    appendDigit(char.digitToInt())

                    if (isSymbolNearby(x, y)) {
                        currentNumberValid = true
                    }

                    currentNumberGears.addAll(findNearbyGears(x, y))
                } else if (char.isEngineSeparator()) {
                    startNewNumber()
                }
            }
        }

        this.partNumbers = partNumbers
        calculateGearRatios(gearCoefficients)
    }

    private fun calculateGearRatios(gearCoefficients: Map<Pair<Int, Int>, List<Int>>) {
        this.gearRatios = gearCoefficients
            .filterValues { it.size == 2 }
            .mapValues { it.value.reduce(Int::times) }
    }

    private fun findNearbyGears(x: Int, y: Int): List<Pair<Int, Int>> {
        val adjacencyMap = getAdjacencyMap(x, y)
        return adjacencyMap.filterValues { it.isEngineGear() }.keys.toList()
    }

    private fun isSymbolNearby(x: Int, y: Int): Boolean {
        val adjacencyMap = getAdjacencyMap(x, y)
        return adjacencyMap.values.any { it.isEngineSymbol() }
    }

    private fun getAdjacencyMap(x: Int, y: Int): Map<Pair<Int, Int>, Char> {
        if (adjacencyMapCache[y][x].isEmpty()) {
            adjacencyMapCache[y][x] = createAdjacencyMap(x, y)
        }

        return adjacencyMapCache[y][x]
    }

    private fun createAdjacencyMap(x: Int, y: Int): Map<Pair<Int, Int>, Char> {
        val adjacencyMap = mutableMapOf<Pair<Int, Int>, Char>()

        val xWest = x - 1
        val xEast = x + 1
        val yNorth = y - 1
        val ySouth = y + 1

        adjacencyMap[Pair(xEast, y)] = array.getOrNull(y)?.getOrNull(xEast) ?: '.'
        adjacencyMap[Pair(xWest, y)] = array.getOrNull(y)?.getOrNull(xWest) ?: '.'
        adjacencyMap[Pair(x, yNorth)] = array.getOrNull(yNorth)?.getOrNull(x) ?: '.'
        adjacencyMap[Pair(x, ySouth)] = array.getOrNull(ySouth)?.getOrNull(x) ?: '.'

        adjacencyMap[Pair(xEast, yNorth)] = array.getOrNull(yNorth)?.getOrNull(xEast) ?: '.'
        adjacencyMap[Pair(xEast, ySouth)] = array.getOrNull(ySouth)?.getOrNull(xEast) ?: '.'
        adjacencyMap[Pair(xWest, yNorth)] = array.getOrNull(yNorth)?.getOrNull(xWest) ?: '.'
        adjacencyMap[Pair(xWest, ySouth)] = array.getOrNull(ySouth)?.getOrNull(xWest) ?: '.'

        return adjacencyMap
    }
}

fun Char.isEngineSymbol(): Boolean {
    return !this.isDigit() && this != '.'
}

fun Char.isEngineSeparator(): Boolean {
    return !this.isDigit()
}

fun Char.isEngineGear(): Boolean {
    return this == '*'
}

fun parseSchematic(): Schematic {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge3/Challenge3.txt")
    return Schematic(file.readLines())
}

fun main() {
    val schematic = parseSchematic()

    val sum = schematic.getPartNumbers().sum()
    println(sum)
}
