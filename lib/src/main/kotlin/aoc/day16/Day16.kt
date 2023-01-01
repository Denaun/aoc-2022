@file:Suppress("UnstableApiUsage")

package aoc.day16

import java.util.*
import kotlin.math.max

fun part1(input: String): Int = maximizeSingleReleasedPressure(parse(input), Valve("AA"), 30)!!

fun part2(input: String): Int = maximizeDoubleReleasedPressure(parse(input), Valve("AA"), 26)

fun maximizeSingleReleasedPressure(scan: Scan, initialValve: Valve, minutes: Int): Int? {
    val simplifiedScan = scan.simplify(initialValve)
    val states = PriorityQueue(compareByDescending(simplifiedScan::upperBoundReleasedPressure))
    states.add(initialState(initialValve, minutes))
    while (states.isNotEmpty()) {
        val state = states.remove()
        val neighbors = simplifiedScan.tunnels.adjacentNodes(state.valve)
            .mapNotNull { state.moveToAndOpen(it, simplifiedScan) }
        states.addAll(neighbors)
        if (neighbors.isEmpty()) {
            return state.busyWait(simplifiedScan).releasedPressure
        }
    }
    return null
}

fun maximizeDoubleReleasedPressure(scan: Scan, initialValve: Valve, minutes: Int): Int {
    val simplifiedScan = scan.simplify(initialValve)
    val solutions = mutableMapOf<Set<Valve>, Int>()
    val toVisit = mutableListOf(initialState(initialValve, minutes))
    while (toVisit.isNotEmpty()) {
        val state = toVisit.removeFirst()
        solutions.merge(state.openValves, state.busyWait(simplifiedScan).releasedPressure, ::max)
        toVisit.addAll(simplifiedScan.tunnels.adjacentNodes(state.valve)
            .mapNotNull { state.moveToAndOpen(it, simplifiedScan) })
    }
    return solutions.maxOf { (valves, releasedPressure) ->
        solutions.filterKeys { (valves intersect it).isEmpty() }.values.maxOf(releasedPressure::plus)
    }
}


fun SimplifiedScan.upperBoundReleasedPressure(state: State): Int {
    var releasedPressure = state.releasedPressure
    var totalFlowRate = state.flowRate(flowRates)
    var minutesLeft = state.minutesLeft
    for (flowRate in flowRates.filterKeys { it !in state.openValves }.values.sortedDescending()) {
        val minutesToMoveAndOpen = 2
        minutesToMoveAndOpen.takeIf { it <= minutesLeft }?.let {
            releasedPressure += totalFlowRate * it
            totalFlowRate += flowRate
            minutesLeft -= it
        }
    }
    return releasedPressure + totalFlowRate * minutesLeft
}

data class State(
    val valve: Valve,
    val minutesLeft: Int,
    val releasedPressure: Int = 0,
    val openValves: Set<Valve> = emptySet(),
) {
    fun moveToAndOpen(other: Valve, scan: SimplifiedScan): State? =
        scan.tunnels.edgeValue(valve, other).orNull()
            ?.takeIf { it < minutesLeft && other !in openValves }?.let {
                State(
                    other,
                    minutesLeft - it - 1,
                    releasedPressure + flowRate(scan.flowRates) * (it + 1),
                    openValves + other,
                )
            }

    fun busyWait(scan: SimplifiedScan) = State(
        valve,
        0,
        releasedPressure + flowRate(scan.flowRates) * minutesLeft,
        openValves,
    )

    fun flowRate(flowRates: Map<Valve, Int>): Int =
        flowRates.filterKeys(openValves::contains).values.sum()
}

fun initialState(valve: Valve, minutesLeft: Int) = State(valve, minutesLeft, 0, emptySet())

fun <T> Optional<T>.orNull(): T? = orElse(null)