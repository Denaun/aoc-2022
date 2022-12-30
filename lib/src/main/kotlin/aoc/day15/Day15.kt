@file:Suppress("UnstableApiUsage")

package aoc.day15

import com.google.common.collect.ContiguousSet
import com.google.common.collect.DiscreteDomain.longs
import com.google.common.collect.Range
import com.google.common.collect.RangeSet
import com.google.common.collect.TreeRangeSet
import kotlin.math.abs

fun part1(input: String): Int = surelyEmptyPositions(parse(input), 2_000_000L).asRanges()
    .sumOf { ContiguousSet.create(it, longs()).size }

fun part2(input: String): Long {
    val distressBeacon = findDistressBeacon(parse(input), 4_000_000L)
    return distressBeacon.x * 4_000_000L + distressBeacon.y
}

fun surelyEmptyPositions(readings: List<Reading>, row: Long): RangeSet<Long> {
    val result = scannedRanges(readings, row)
    result.removeAll(readings.filter { it.beacon.y == row }.map { Range.singleton(it.beacon.x) })
    return result
}

fun findDistressBeacon(readings: List<Reading>, maxCoordinate: Long): Position {
    val (row, possibleXs) = (0L..maxCoordinate).asSequence().map { row ->
        val possibleXs = scannedRanges(readings, row).complement()
        possibleXs.remove(Range.lessThan(0))
        possibleXs.remove(Range.greaterThan(maxCoordinate))
        row to possibleXs
    }.find { !it.second.isEmpty }!!
    return Position(ContiguousSet.create(possibleXs.span(), longs()).single(), row)
}

fun scannedRanges(readings: List<Reading>, row: Long): RangeSet<Long> {
    val result = TreeRangeSet.create<Long>()
    result.addAll(readings.mapNotNull {
        val emptyRadius = it.emptyRadius() - abs(row - it.sensor.y)
        if (emptyRadius >= 0) {
            Range.closed(it.sensor.x - emptyRadius, it.sensor.x + emptyRadius)
        } else {
            null
        }
    })
    return result
}

data class Position(val x: Long, val y: Long) {
    infix fun manhattanFrom(other: Position): Long = abs(x - other.x) + abs(y - other.y)
}

data class Reading(val sensor: Position, val beacon: Position) {
    fun emptyRadius(): Long = sensor manhattanFrom beacon
}
