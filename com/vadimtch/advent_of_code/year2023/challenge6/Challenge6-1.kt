package com.vadimtch.advent_of_code.year2023.challenge6

import java.io.File
import kotlin.math.*

class Race(
    private val record: Int,
    private val duration: Int
) {

    fun countSuccessfulHoldTimes(): Int {
        val times = 0..duration

        return times.count { doesBeatRecord(it) }
    }

    fun countSuccessfulHoldTimes2(): Int {
        /*
        * alternative maths-based solution
        *
        * speed = hold_time
        * distance = speed * move_time = speed * (duration - hold_time) =
        * = hold_time * (duration - hold_time) = -hold_time^2 + duration * hold_time
        *
        * speed > record
        * -hold_time^2 + duration * hold_time > record
        * -hold_time^2 + duration * hold_time - record > 0
        * hold_time^2 - duration * hold_time + record < 0
        *
        * solving in hold_time:
        * discriminant = duration^2 - 4 * record
        * roots = (duration +- sqrt(discriminant)) / 2
        * range = (root1, root2)
         */

            val discriminant = duration.toDouble().pow(2) - 4 * record
        val discriminantSqrt = sqrt(discriminant)

        val root1 = (duration - discriminantSqrt) / 2
        val root2 = (duration + discriminantSqrt) / 2

        // The closest integer to vertex strictly greater than root 1 [to the left of vertex]
        // Root 1 is to the left of vertex => root + 1, if integer; ceil otherwise
        val minimumHoldTime = if (root1 % 1 == 0.0) {
            root1.toInt() + 1
        } else {
            ceil(root1).toInt()
        }

        // The closest integer to vertex strictly less than root 2
        // Root 2 is to the right of vertex => root - 1, if integer; floor otherwise
        val maximumHoldTime = if (root2 % 1 == 0.0) {
            root2.toInt() - 1
        } else {
            floor(root2).toInt()
        }

        return ((maximumHoldTime - minimumHoldTime) + 1)
    }

    private fun doesBeatRecord(holdTime: Int): Boolean {
        return calculateDistance(holdTime) > record
    }

    private fun calculateDistance(holdTime: Int): Int {
        val speed = 1 * holdTime
        val moveTime = duration - holdTime

        return speed * moveTime
    }

}

fun loadRaces(): List<Race> {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge6/Challenge6.txt")

    val data = file.readLines()
        .asSequence()
        .map { it.split(":") }
        .map { it[1] }
        .map { it.trim() }
        .map { it.split(" ") }
        .map { it.filter { token -> token.isNotEmpty()} }
        .map { it.map { token -> token.toInt() } }
        .toList()

    val races = mutableListOf<Race>()
    data.first().forEachIndexed { i, _ ->
        val time = data[0][i]
        val record = data[1][i]

        races.add(Race(record, time))
    }

    return races
}

fun main() {
    val races = loadRaces()

    val product = races
        .map { it.countSuccessfulHoldTimes2() }
        .reduce(Int::times)

    println(product)
}
