package com.vadimtch.advent_of_code.year2024.challenge5

// The problem here is very similar in nature to a scheduling problem from the critical path analysis world.
// Treating the rule-set as a dependency table / graph, we can find the desired ordering
// as the topological sort of said graph.

class DependencyGraph(items: List<Int>, dependencies: List<OrderRule>) {
    private val nodeDependents: MutableMap<Int, MutableSet<Int>> = items
        .associateWith { item ->
            dependencies
                .filter { it.earlyPage == item && it.latePage in items }
                .map { it.latePage }
                .toMutableSet()
        }
        .toMutableMap()

    private val nodeDependencies: MutableMap<Int, MutableSet<Int>> = items
        .associateWith { item ->
            dependencies
                .filter { it.earlyPage in items && it.latePage == item }
                .map { it.earlyPage }
                .toMutableSet()
        }
        .toMutableMap()

    val sortedNodes = mutableListOf<Int>()

    init {
        kahnSort()
    }

    // This produces a valid topological sort of the graph if and only if the graph is a DAG.
    // This should always be the case, and is not explicitly verified.
    private fun kahnSort() {
        val freeNodes = this.nodeDependencies
            .filterValues { it.isEmpty() }
            .keys
            .toMutableList()

        while (freeNodes.isNotEmpty()) {
            val item = freeNodes.removeFirst()
            sortedNodes.add(item)

            this.nodeDependents[item]?.forEach { dependent ->
                this.nodeDependencies[dependent]!!.remove(item)

                if (this.nodeDependencies[dependent]!!.isEmpty()) {
                    freeNodes.add(dependent)
                }
            }
        }
    }
}

fun main() {
    val (rules, updates) = parseInput()

    val indexedUpdates = updates.mapIndexed { index, update -> Pair(update.dependencyIndexed(), index) }

    val sum = indexedUpdates
        .asSequence()
        .filter { indexedUpdate -> rules.any { !it.verify(indexedUpdate.first) } }
        .map { indexedUpdate -> indexedUpdate.second }
        .map { DependencyGraph(updates[it], rules) }
        .map { it.sortedNodes }
        .fold(0) { sum, list ->
            sum + list[list.size / 2]
        }

    println(sum)
}


