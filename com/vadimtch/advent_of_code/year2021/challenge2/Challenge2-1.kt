package com.vadimtch.advent_of_code.year2021.challenge2

import java.io.File

class SubmarinePosition(var xAxis: Int = 0, var zAxis: Int = 0) {
    fun updatePosition(command: String) {
        val tokens = command.split(" ")

        when(tokens[0]) {
            "forward" -> xAxis += Integer.parseInt(tokens[1])
            "up" -> zAxis += Integer.parseInt(tokens[1])
            "down" -> zAxis -= Integer.parseInt(tokens[1])
        }
    }
}

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2021/challenge2/Challenge2.txt")
    val position = SubmarinePosition()

    file.forEachLine {
        position.updatePosition(it)
    }

    println(position.xAxis * (-position.zAxis))
}