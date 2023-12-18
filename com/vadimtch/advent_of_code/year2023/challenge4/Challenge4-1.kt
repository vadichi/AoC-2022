package com.vadimtch.advent_of_code.year2023.challenge4

import java.io.File
import kotlin.math.pow

class Card(descriptor: String) {
    private val id: Int
    private val cardNumbers: Set<Int>
    private val winningNumbers: Set<Int>

    init {
        fun parseNumberSet(descriptor: String): Set<Int> {
            return descriptor
                .trim()
                .split(" ")
                .filter { it.isNotEmpty() }
                .map { it.toInt() }
                .toSet()
        }

        val tokens = descriptor
            .split(":", "|")
            .map { it.trim() }

        id = tokens[0]
            .split(" ")
            .filter { it.isNotEmpty() }[1]
            .toInt()

        cardNumbers = parseNumberSet(tokens[1])
        winningNumbers = parseNumberSet(tokens[2])
    }

    fun getId(): Int {
        return id
    }

    fun calculatePoints(): Int {
        return 2.0.pow(calculateMatches() - 1).toInt()
    }

    fun calculateWins(): Int {
        return calculateMatches()
    }

    private fun calculateMatches(): Int {
        return cardNumbers.intersect(winningNumbers).size
    }
}

fun loadCards(): List<Card> {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge4/Challenge4.txt")

    return file
        .readLines()
        .map { Card(it) }
}

fun main() {
    val cards = loadCards()

    val sum = cards
        .sumOf { it.calculatePoints() }

    println(sum)
}
