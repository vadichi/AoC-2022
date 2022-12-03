package com.vadimtch.advent_of_code.year2022.challenge1

import java.io.File

fun readCalories() : ArrayList<Int> {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge1/Challenge1.txt")

    val calories = arrayListOf<Int>(0)
    var index = 0

    file.forEachLine {
        if (it == "") {
            calories.add(0)
            index++
        } else {
            calories[index] += Integer.parseInt(it)
        }
    }

    calories.sortDescending()
    return calories
}

fun main() {
    val calories = readCalories()

    println(calories[0])
}