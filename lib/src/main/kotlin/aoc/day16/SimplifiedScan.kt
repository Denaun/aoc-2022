@file:Suppress("UnstableApiUsage")

package aoc.day16

import com.google.common.graph.ImmutableValueGraph
import com.google.common.graph.ValueGraphBuilder

data class SimplifiedScan(
    val flowRates: Map<Valve, Int>,
    val tunnels: ImmutableValueGraph<Valve, Int>,
)

fun Scan.simplify(initialValve: Valve): SimplifiedScan {
    val usefulFlowRates = flowRates.filterValues { it > 0 }
    val usefulDistances = tunnels.nodes().filter { it == initialValve || it in usefulFlowRates }
        .associateWith { distances(it).filterKeys(usefulFlowRates::containsKey) }
    val flattenedTunnels = ValueGraphBuilder.undirected().immutable<Valve, Int>()
    for ((start, distances) in usefulDistances) {
        for ((end, distance) in distances) {
            flattenedTunnels.putEdgeValue(start, end, distance)
        }
    }
    return SimplifiedScan(usefulFlowRates, flattenedTunnels.build())
}
