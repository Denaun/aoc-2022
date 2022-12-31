@file:Suppress("UnstableApiUsage")

package aoc.day16

import com.google.common.graph.ImmutableGraph

data class Valve(val label: String)

data class Scan(val flowRates: Map<Valve, Int>, val tunnels: ImmutableGraph<Valve>) {
    fun distances(start: Valve): Map<Valve, Int> {
        val toVisit = tunnels.adjacentNodes(start).toMutableList()
        val visited = mutableSetOf(start)
        val distances = mutableMapOf<Valve, Int>()
        var distance = 1
        while (toVisit.isNotEmpty()) {
            repeat(toVisit.size) {
                val current = toVisit.removeFirst()
                if (current !in visited) {
                    distances[current] = distance
                    toVisit.addAll(tunnels.adjacentNodes(current))
                }
                visited.add(current)
            }
            distance += 1
        }
        return distances.toMap()
    }
}