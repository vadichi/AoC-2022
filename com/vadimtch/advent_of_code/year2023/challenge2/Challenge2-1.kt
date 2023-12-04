package com.vadimtch.advent_of_code.year2023.challenge2

import java.io.File

const val MAX_RED = 12
const val MAX_GREEN = 13
const val MAX_BLUE = 14

data class Turn(

    var red: Int = 0,
    var green: Int = 0,
    var blue: Int = 0,

) {

    fun isPossible(): Boolean {
        return red <= MAX_RED && green <= MAX_GREEN && blue <= MAX_BLUE
    }

    fun power(): Int {
        return red * green * blue
    }

    companion object {
        fun from(descriptor: String): Turn {
            val turn = Turn()
            val tokens = descriptor.split(",")

            tokens.forEach { token ->
                val subTokens = token.trim().split(" ")
                val quantity = subTokens[0].toInt()
                val colour = subTokens[1]

                when (colour) {
                    "red" -> {
                        turn.red += quantity
                    }
                    "green" -> {
                        turn.green += quantity
                    }
                    "blue" -> {
                        turn.blue += quantity
                    }
                }
            }

            return turn
        }

    }

}

data class Game(

    val id: Int,
    val turns: List<Turn>

) {

    fun isPossible(): Boolean {
        return this.turns.all { turn -> turn.isPossible() }
    }

    fun minimumBallSet(): Turn {
        val red = this.turns.maxOf { turn -> turn.red }
        val green = this.turns.maxOf { turn -> turn.green }
        val blue = this.turns.maxOf { turn -> turn.blue }

        return Turn(red, green, blue)
    }

    companion object {
        fun from(descriptor: String): Game {
            val tokens = descriptor.split(":")
            val id = tokens[0].split(" ")[1].toInt()

            val turns = mutableListOf<Turn>()
            val turnDescriptors = tokens[1].split(";")

            turnDescriptors.forEach { turnDescriptor ->
                turns.add(Turn.from(turnDescriptor))
            }

            return Game(id, turns)
        }
    }

}

fun parseGames(): List<Game> {
    val file = File("com/vadimtch/advent_of_code/year2023/challenge2/Challenge2.txt")

    val games = mutableListOf<Game>()
    file.forEachLine { line ->
        val game = Game.from(line)
        games.add(game)
    }

    return games
}

fun main() {
    val games = parseGames()

    val idSum = games
        .filter { game -> game.isPossible() }
        .sumOf { game -> game.id }

    print(idSum)
}
