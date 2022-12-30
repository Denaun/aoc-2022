package aoc.day14

import kotlin.math.max
import kotlin.math.min

fun part1(input: String): Int = simulateCave(parse(input), ::simulateFloorless)

fun part2(input: String): Int = simulateCave(parse(input), ::simulateFloorful)

fun simulateCave(
    paths: List<Path>,
    simulateUnit: (originalMaxY: Int, occupiedPoints: Set<Point>) -> Point?,
): Int {
    val occupiedPoints = paths.flatten().toMutableSet()
    val emptySize = occupiedPoints.size
    val originalMaxY = occupiedPoints.maxOf { it.y }
    occupiedPoints.addAll(generateSequence { simulateUnit(originalMaxY, occupiedPoints) })
    return occupiedPoints.size - emptySize
}

fun simulateFloorless(originalMaxY: Int, occupiedPoints: Set<Point>): Point? {
    val start = Point(500, 0)
    require(!occupiedPoints.contains(start))
    val end = start.fall(occupiedPoints).takeWhile { it.y <= originalMaxY }.last()
    return if (end.y < originalMaxY) {
        end
    } else {
        null
    }
}

fun simulateFloorful(originalMaxY: Int, occupiedPoints: Set<Point>): Point? {
    val start = Point(500, 0)
    if (occupiedPoints.contains(start)) {
        return null
    }
    val floor = 2 + originalMaxY
    return start.fall(occupiedPoints).takeWhile { it.y < floor }.last()
}

data class Point(val x: Int, val y: Int) {
    fun fall(occupiedPoints: Set<Point>): Sequence<Point> =
        generateSequence(this) { it.neighbors().find { next -> !occupiedPoints.contains(next) } }

    private fun neighbors(): List<Point> = listOf(
        Point(x, y + 1),
        Point(x - 1, y + 1),
        Point(x + 1, y + 1),
    )
}

data class Path(val points: List<Point>) : Iterable<Point> {
    override fun iterator(): Iterator<Point> =
        points.zipWithNext().asSequence().flatMap { (start, end) ->
            if (start.x == end.x) {
                (min(start.y, end.y)..max(start.y, end.y)).map { Point(start.x, it) }
            } else {
                require(start.y == end.y)
                (min(start.x, end.x)..max(start.x, end.x)).map { Point(it, start.y) }
            }
        }.iterator()
}