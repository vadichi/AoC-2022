package com.vadimtch.advent_of_code.year2022.challenge7

import java.io.File

class BetterFileSystem(sourceFile: File) : FileSystem(sourceFile) {
    fun smallestDirectoryOverLimit(sizeLimit: Int): Int {
        val sizesOverLimit = ArrayList<Int>()

        forEachDirectory(root) { directory ->
            if (directory.size >= sizeLimit) {
                sizesOverLimit.add(directory.size)
            }
        }

        return sizesOverLimit.min()
    }
}

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge7/Challenge7.txt")
    val fileSystem = BetterFileSystem(file)

    val spaceTotal = 70_000_000
    val spaceRequired = 30_000_000

    val spaceUsed = fileSystem.root.size
    val spaceFree = spaceTotal - spaceUsed
    val spaceDelta = spaceRequired - spaceFree

    if (spaceDelta < 0) {
        println("There is enough space already")
        return
    }

    println(fileSystem.smallestDirectoryOverLimit(spaceDelta))
}