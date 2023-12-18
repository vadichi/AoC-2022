package com.vadimtch.advent_of_code.year2023.challenge4

fun main() {
    val cards = loadCards()
    val myCards = cards
        .associate { Pair(it.getId(), 1) }
        .toMutableMap()

    myCards.keys.forEach {
        val index = it - 1
        val card = cards[index]
        val cardsWon = card.calculateWins()

        val count = myCards[it]!!
        for (i in 1 .. cardsWon) {
            myCards[it + i] = myCards[it + i]!! + count
        }
    }

    val count = myCards.values.sum()
    println(count)
}
