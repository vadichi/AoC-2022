package com.vadimtch.advent_of_code.year2022.challenge3

import java.io.File

class PriorityCalculator() {
    private val priorities = HashMap<Char, Int>();

    init {
        var letter = 'a'
        var priority = 1
        while (letter <= 'z') {
            priorities[letter] = priority
            letter++
            priority++
        }
        letter = 'A'
        while (letter <= 'Z') {
            priorities[letter] = priority
            letter++
            priority++
        }
    }

    fun getPriority(item: Char): Int {
        return priorities[item]!!
    }
}

fun findMatch(string1: String, string2: String): Char {
    val items = HashSet<Char>()

    for (char in string1) {
        items.add(char)
    }

    for (char in string2) {
        if (items.contains(char)) {
            return char
        }
    }

    throw IllegalArgumentException()
}

fun main() {
    val priorityCalculator = PriorityCalculator()
    val file = File("com/vadimtch/advent_of_code/year2022/challenge3/Challenge3.txt")

    var total = 0
    file.forEachLine {
        val middleIndex = it.length / 2;
        val compartment1 = it.substring(0, middleIndex)
        val compartment2 = it.substring(middleIndex)

        val duplicate = findMatch(compartment1, compartment2)
        total += priorityCalculator.getPriority(duplicate)
    }

    print(total)
}