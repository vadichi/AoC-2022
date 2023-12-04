package com.vadimtch.advent_of_code.year2023.challenge2

fun main() {
    val games = parseGames()

    val powerSum = games
        .map { game -> game.minimumBallSet() }
        .sumOf { set -> set.power() }

    println(powerSum)
}
