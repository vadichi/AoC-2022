package com.vadimtch.advent_of_code.year2021.challenge2

import java.io.File

class BetterSubmarinePosition(var xAxis: Int = 0, var zAxis: Int = 0, private var pitch: Int = 0) {
    fun updatePosition(command: String) {
        val tokens = command.split(" ")

        when(tokens[0]) {
            "forward" -> {
                xAxis += Integer.parseInt(tokens[1])
                zAxis += pitch * Integer.parseInt(tokens[1])
            }

            "up" -> pitch += Integer.parseInt(tokens[1])
            "down" -> pitch -= Integer.parseInt(tokens[1])
        }
    }
}

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2021/challenge2/Challenge2.txt")
    val position = BetterSubmarinePosition()

    file.forEachLine {
        position.updatePosition(it)
    }

    println(position.xAxis * (-position.zAxis))
}