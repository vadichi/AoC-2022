package com.vadimtch.advent_of_code.year2022.challenge7

import java.io.File

open class FileSystemNode(var size: Int)
class FileNode(size: Int) : FileSystemNode(size)
class DirectoryNode(val parent: DirectoryNode? = null, size: Int = 0, val children: HashMap<String, FileSystemNode> = HashMap()) : FileSystemNode(size)

open class FileSystem(sourceFile: File) {
    val root: DirectoryNode = DirectoryNode()
    private var currentDirectory = root

    init {
        sourceFile.forEachLine { line ->
            if (line.startsWith("$")) {
                parseCommand(line)
            } else {
                parseNode(line)
            }
        }

        calculateSize(root)
    }

    fun sumDirectoriesUnderLimit(sizeLimit: Int): Int {
        var sum = 0

        forEachDirectory(root) { directory ->
            if (directory.size <= sizeLimit) {
                sum += directory.size
            }
        }

        return sum
    }

    private fun parseCommand(line: String) {
        val tokens = line.split(" ")

        when(tokens[1]) {
            "cd" -> {
                val targetDirectory = tokens[2]

                currentDirectory = when(targetDirectory) {
                    "/" -> root
                    ".." -> currentDirectory.parent!!
                    else -> currentDirectory.children[targetDirectory]!! as DirectoryNode
                }
            }
        }
    }

    private fun parseNode(line: String) {
        val tokens = line.split(" ")
        val name = tokens[1]

        if (tokens[0] == "dir") {
            val directory = DirectoryNode(currentDirectory)
            currentDirectory.children[name] = directory
        } else {
            val size = tokens[0].toInt()

            val file = FileNode(size)
            currentDirectory.children[name] = file
        }
    }

    private fun calculateSize(root: DirectoryNode) {
        forEachDirectory(root) { directory ->
            directory.size = 0

            for (node in directory.children) {
                directory.size += node.value.size
            }
        }
    }

    protected fun forEachDirectory(directory: DirectoryNode, action: (DirectoryNode) -> Unit) {
        for (node in directory.children) {
            if (node.value is DirectoryNode) {
                forEachDirectory(node.value as DirectoryNode, action)
            }
        }

        action.invoke(directory)
    }
}

fun main() {
    val file = File("com/vadimtch/advent_of_code/year2022/challenge7/Challenge7.txt")
    val fileSystem = FileSystem(file)

    println(fileSystem.sumDirectoriesUnderLimit(100_000))
}