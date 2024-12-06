package com.vadimtch.advent_of_code.year2024.challenge5

import java.io.File

// List of page numbers.
typealias Update = List<Int>

// Map of page numbers to indices they appeared at in the original list (unique).
typealias IndexedUpdate = Map<Int, Int>

data class OrderRule(val earlyPage: Int, val latePage: Int) {
    fun verify(update: IndexedUpdate): Boolean =
        update[earlyPage] == null || update[latePage] == null ||
        update[earlyPage]!! < update[latePage]!!
}

fun parseOrderRules(lines: List<String>): List<OrderRule> =
     lines.map { line ->
        val parts = line
            .trim()
            .split("|")
            .map { it.toInt() }

        OrderRule(parts[0], parts[1])
    }

fun Update.dependencyIndexed(): IndexedUpdate =
    this.mapIndexed { index, page -> page to index }.toMap()

fun parseUpdates(lines: List<String>): List<Update> =
    lines.map { line ->
        line
            .trim()
            .split(",")
            .map { it.toInt() }
    }

fun parseInput(): Pair<List<OrderRule>, List<Update>> {
    val file = File("com/vadimtch/advent_of_code/year2024/challenge5/Challenge5.txt")

    val lines = file.readLines()
    val separatorIndex = lines.indexOfFirst { it.isBlank() }

    return Pair(
        parseOrderRules(lines.subList(0, separatorIndex)),
        parseUpdates(lines.subList(separatorIndex + 1, lines.size))
    )
}

fun main() {
    val (rules, updates) = parseInput()

    // When summing the middle page of each update at the end,
    // it is useful to return to the original list representation.
    // So, each indexed update is paired with the index of the corresponding original update in the list of those.

    // Assumes each page appears no more than once in any given update.
    val indexedUpdates = updates.mapIndexed { index, update -> Pair(update.dependencyIndexed(), index) }

    val validUpdateSum = indexedUpdates
        .filter { indexedUpdate -> rules.all { it.verify(indexedUpdate.first) } }
        .fold(0) { sum, indexedUpdate ->
            val update = updates[indexedUpdate.second]

            // Assumes odd-sized updates.
            sum + update[update.size / 2]
        }

    println(validUpdateSum)
}
