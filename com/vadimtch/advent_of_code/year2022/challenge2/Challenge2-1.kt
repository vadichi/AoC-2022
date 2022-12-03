package com.vadimtch.advent_of_code.year2022.challenge2

import java.io.File

fun calculateScore(scoreMap: HashMap<String, IntArray>) : Int {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge2/Challenge2.txt")

    var score = 0
    file.forEachLine {
        val gameScores = scoreMap[it]!!

        score += gameScores[0]
        score += gameScores[1]
    }

    return score
}

fun main() {
    // Score for: [Game result, picked shape]
    val scores = hashMapOf(
        "A X" to intArrayOf(3, 1), // Rock <- Rock | Draw
        "A Y" to intArrayOf(6, 2), // Rock <- Paper | Won
        "A Z" to intArrayOf(0, 3), // Rock <- Scissors | Lost
        "B X" to intArrayOf(0, 1), // Paper <- Rock | Lost
        "B Y" to intArrayOf(3, 2), // Paper <- Paper | Draw
        "B Z" to intArrayOf(6, 3), // Paper <- Scissors | Won
        "C X" to intArrayOf(6, 1), // Scissors <- Rock | Won
        "C Y" to intArrayOf(0, 2), // Scissors <- Paper | Lost
        "C Z" to intArrayOf(3, 3) // Scissors <- Scissors | Draw
    )

    print(calculateScore(scores))
}