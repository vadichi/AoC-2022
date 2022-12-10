package com.vadimtch.advent_of_code.year2022.challenge10

import java.io.EOFException
import java.io.File
import kotlin.math.abs

class CRT(private val processor: Processor) {
    private var currentX = 0
    private var currentY = 0
    private val screen = Array(40) { Array(6) { '.' } }

    fun onTick() {
        val distance = abs(processor.xRegister - currentX)

        if (distance <= 1) {
            screen[currentX][currentY] = '#'
        }

        currentX++
        if (currentX >= 40) {
            currentX = 0
            currentY++
        }
    }

    fun printScreen() {
        for (y in 0 until 6) {
            for (x in 0 until 40) {
                print(screen[x][y])
            }

            println()
        }
    }
}

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge10/Challenge10.txt")
    val processor = Processor(file.bufferedReader())
    val crt = CRT(processor)

    while(true) {
        try {
            processor.onTick()
        } catch (e: EOFException) {
            break
        } finally {
            crt.onTick()
        }
    }

    crt.printScreen()
}