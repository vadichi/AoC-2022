package com.vadimtch.advent_of_code.year2021.challenge1

import java.io.File
import java.util.Collections


fun main() {
    val file = File("com/vadimtch/advent_of_code/year2021/challenge1/Challenge1.txt")
    val reader = file.bufferedReader()

    val measurementsWindow = arrayListOf(0, 0, 0)
    var line = reader.readLine()

    var lastSum = 0
    var increasedSums = 0
    do {
        Collections.rotate(measurementsWindow, 1)
        measurementsWindow[0] = Integer.parseInt(line)

        val sum = measurementsWindow.sum()
        if (sum > lastSum) {
            increasedSums++
        }

        lastSum = sum
        line = reader.readLine()
    } while(line != null)

    // Do not count the first three increases (list not properly filled yet)
    increasedSums -= 3

    print(increasedSums)
}