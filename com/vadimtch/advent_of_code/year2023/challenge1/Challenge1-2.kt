package com.vadimtch.advent_of_code.year2023.challenge1

val DIGIT_SPELLINGS = mapOf(
    "one" to 1,
    "two" to 2,
    "three" to 3,
    "four" to 4,
    "five" to 5,
    "six" to 6,
    "seven" to 7,
    "eight" to 8,
    "nine" to 9,
)

fun Map<String, Int>.startWith(string: String): List<String> {
    return this.filterKeys {
        key -> key.startsWith(string)
    }.keys.toList()
}

fun replaceDigits(line: String): String {
    var modifiedLine = line

    var start = 0
    var end = 0

    fun nextStart() {
        start += 1
        end = start
    }

    do {
        val substring = modifiedLine.substring(start, end + 1)
        val matchingDigits = DIGIT_SPELLINGS.startWith(substring)

        if (matchingDigits.isEmpty()) {
            nextStart()
        }
        else if (matchingDigits[0] == substring) {
            modifiedLine = modifiedLine.substring(0, start) + DIGIT_SPELLINGS[substring] + modifiedLine.substring(end + 1)
            nextStart()
        } else {
            end += 1

            if (end >= modifiedLine.length) {
                nextStart()
            }
        }
    } while (end < modifiedLine.length)

    println(modifiedLine)
    return modifiedLine
}

fun hack(lines: List<String>): Int {
    return lines.map { line ->
        var l = line

        val first = l.findAnyOf(DIGIT_SPELLINGS.keys)
        if (first != null) {
            l = l.substring(0, first.first) + DIGIT_SPELLINGS[first.second].toString() + l.substring(first.first + first.second.length)
        }

        val last = l.findLastAnyOf(DIGIT_SPELLINGS.keys)
        if (last != null) {
            l = l.substring(0, last.first) + DIGIT_SPELLINGS[last.second].toString() + l.substring(last.first + last.second.length)
        }

        l
    }.sumOf {
        line -> calculateCalibrationNumber(line)
    }
}

fun main() {
    val sum = readLines()
        .map { line ->
            replaceDigits(line)
        }
        .sumOf { line ->
            calculateCalibrationNumber(line)
        }

    println(sum)
    println(hack(readLines()))
}
