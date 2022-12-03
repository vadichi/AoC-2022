package com.vadimtch.advent_of_code.year2022.challenge3

import java.io.File

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge3/Challenge3.txt")
    val priorityCalculator = PriorityCalculator()
    val reader = file.bufferedReader()

    var line = reader.readLine()
    var total = 0
    do {
        val items = HashMap<Char, Int>()

        next_group@ for (i in 1..3) {
            val itemsFound = HashSet<Char>()

            for (char in line) {
                if (itemsFound.contains(char)) {
                    continue
                }

                if (items[char] == null) {
                    items[char] = 1
                    itemsFound.add(char)
                } else if (items[char] == 1) {
                    items[char] = 2
                    itemsFound.add(char)
                } else if (items[char] == 2) {
                    total += priorityCalculator.getPriority(char)

                    line = reader.readLine()
                    break@next_group
                }
            }

            line = reader.readLine()
        }
    } while(line != null)

    print(total)
}