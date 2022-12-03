package com.vadimtch.advent_of_code.year2022.challenge2

fun main() {
    // Score for: [Game result, picked shape]
    val scores = hashMapOf(
        "A X" to intArrayOf(0, 3), // Rock <- [Scissors] | Lost
        "A Y" to intArrayOf(3, 1), // Rock <- [Rock] | Draw
        "A Z" to intArrayOf(6, 2), // Rock <- [Paper] | Won
        "B X" to intArrayOf(0, 1), // Paper <- [Rock] | Lost
        "B Y" to intArrayOf(3, 2), // Paper <- [Paper] | Draw
        "B Z" to intArrayOf(6, 3), // Paper <- [Scissors] | Won
        "C X" to intArrayOf(0, 2), // Scissors <- [Paper] | Lost
        "C Y" to intArrayOf(3, 3), // Scissors <- [Scissors] | Draw
        "C Z" to intArrayOf(6, 1) // Scissors <- [Rock] | Won
    )

    print(calculateScore(scores))
}