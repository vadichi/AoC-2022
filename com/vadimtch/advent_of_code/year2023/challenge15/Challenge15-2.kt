package com.vadimtch.advent_of_code.year2023.challenge15

data class Lens (
    var focalLength: Int,
    val label: String,
)

class Box {
    private val lenses = mutableListOf<Lens>()

    fun calculateLensPower(): Int {
        return lenses.mapIndexed { index, lens ->
            lens.focalLength * (index + 1)
        }.sum()
    }

    fun addLens(newLens: Lens) {
        val oldLens = lenses.find { it.label == newLens.label }
        if (oldLens != null) {
            oldLens.focalLength = newLens.focalLength
        } else {
            lenses.add(newLens)
        }
    }

    fun removeLens(label: String) {
        lenses.removeIf { it.label == label }
    }

}

fun main() {
    val sequence = parseInput()
    val boxes = Array(256) { Box() }

    sequence.forEach { step ->
        val lensLabel = step.getLensLabel()
        val boxNumber = step.getBoxNumber()

        when(step.getOperationCode()) {
            '-' -> {
                boxes[boxNumber].removeLens(lensLabel)
            }

            '=' -> {
                val lensFocalLength = step.getLensFocalLength()!!
                val lens = Lens(lensFocalLength, lensLabel)

                boxes[boxNumber].addLens(lens)
            }
        }
    }

    val powerSum = boxes.mapIndexed { index, box ->
        box.calculateLensPower() * (index + 1)
    }.sum()

    println(powerSum)
}
