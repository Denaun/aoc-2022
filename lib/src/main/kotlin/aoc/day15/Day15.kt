@file:Suppress("UnstableApiUsage")

package aoc.day15

import com.google.common.collect.ContiguousSet
import com.google.common.collect.DiscreteDomain.integers
import com.google.common.collect.Range
import com.google.common.collect.RangeSet
import com.google.common.collect.TreeRangeSet
import kotlin.math.abs

fun part1(input: String): Int = surelyEmptyPositions(parse(input), 2_000_000).asRanges()
    .sumOf { ContiguousSet.create(it, integers()).size }

fun surelyEmptyPositions(readings: List<Reading>, row: Int): RangeSet<Int> {
    val result = TreeRangeSet.create<Int>()
    result.addAll(readings.mapNotNull {
        val center = Position(it.sensor.x, row)
        val emptyRadius = it.emptyRadius() - (center manhattanFrom it.sensor)
        if (emptyRadius >= 0) {
            Range.closed(center.x - emptyRadius, center.x + emptyRadius)
        } else {
            null
        }
    })
    result.removeAll(readings.filter { it.beacon.y == row}.map { Range.singleton(it.beacon.x) })
    return result
}

data class Position(val x: Int, val y: Int) {
    infix fun manhattanFrom(other: Position): Int = abs(x - other.x) + abs(y - other.y)
}

data class Reading(val sensor: Position, val beacon: Position) {
    fun emptyRadius(): Int = sensor manhattanFrom beacon
}
