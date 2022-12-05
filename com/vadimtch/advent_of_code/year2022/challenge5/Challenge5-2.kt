package com.vadimtch.advent_of_code.year2022.challenge5

import java.io.BufferedReader
import java.io.File

class BetterContainerShip(reader: BufferedReader) : ContainerShip(reader) {
    override fun swap(instruction: Instruction) {
        val buffer = ArrayDeque<Char>()

        repeat(instruction.count) {
            val element = state[instruction.origin].removeLast()
            buffer.addLast(element)
        }

        repeat(instruction.count) {
            val element = buffer.removeLast()
            state[instruction.destination].addLast(element)
        }
    }
}

fun main() {
    val reader = File("com/vadimtch/advent_of_code/year2022/challenge5/Challenge5.txt").bufferedReader()
    val containerShip = BetterContainerShip(reader)

    println(containerShip.getTopLayer())
}