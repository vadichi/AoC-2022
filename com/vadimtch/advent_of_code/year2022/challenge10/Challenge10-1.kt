package com.vadimtch.advent_of_code.year2022.challenge10

import java.io.BufferedReader
import java.io.EOFException
import java.io.File

data class Instruction(
    var cyclesRemaining: Int,
    val action: (Processor) -> Unit
)

class Processor(private val instructionReader: BufferedReader) {
    var cycle: Int = 0
    var xRegister: Int = 1

    private var instruction: Instruction = Instruction(0) {}

    fun onTick() {
        if (instruction.cyclesRemaining == 0) {
            instruction.action.invoke(this)

            getNextInstruction()
        }

        instruction.cyclesRemaining--
        cycle++
    }

    private fun getNextInstruction() {
        val line = instructionReader.readLine() ?: throw EOFException()

        val tokens = line.split(" ")
        instruction = when (tokens[0]) {
            "noop" -> Instruction(1) { }
            "addx" -> Instruction(2) { xRegister += tokens[1].toInt() }
            else -> Instruction(0) { }
        }
    }
}

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge10/Challenge10.txt")
    val processor = Processor(file.bufferedReader())

    var signalStrengthsSum = 0
    while(true) {
        try { processor.onTick() } catch (e: EOFException) { break }

        if (processor.cycle % 40 == 20) {
            signalStrengthsSum += processor.xRegister * processor.cycle
        }
    }

    println(signalStrengthsSum)
}