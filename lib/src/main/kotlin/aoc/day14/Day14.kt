package aoc.day14

import kotlin.math.max
import kotlin.math.min

fun part1(input: String): Int = simulateCave(parse(input))

fun simulateCave(paths: List<Path>): Int {
    val occupiedPoints = paths.flatten().toMutableSet()
    val emptySize = occupiedPoints.size
    occupiedPoints.addAll(generateSequence { simulateUnit(occupiedPoints) })
    return occupiedPoints.size - emptySize
}

fun simulateUnit(occupiedPoints: Set<Point>): Point? {
    var point = Point(500, 0)
    require(!occupiedPoints.contains(point))
    val freeFallThreshold = occupiedPoints.maxOf { it.y }
    while (point.y < freeFallThreshold) {
        point = point.neighbors().find { !occupiedPoints.contains(it) } ?: return point
    }
    return null
}

data class Point(val x: Int, val y: Int) {
    fun neighbors(): List<Point> = listOf(
        Point(x, y + 1),
        Point(x - 1, y + 1),
        Point(x + 1, y + 1),
    )
}

data class Path(val points: List<Point>) : Iterable<Point> {
    override fun iterator(): Iterator<Point> =
        points.zipWithNext().asSequence().flatMap { (start, end) ->
            if (start.x == end.x) {
                (min(start.y, end.y) .. max(start.y, end.y)).map { Point(start.x, it) }
            } else {
                require(start.y == end.y)
                (min(start.x, end.x) .. max(start.x, end.x)).map { Point(it, start.y) }
            }
        }.iterator()
}