package aoc.day09

import kotlin.math.abs

fun part1(input: String): Int = simulate(parse(input)).toSet().size

fun simulate(motions: List<Motion>): List<Coordinate> {
    var head = Coordinate(0, 0)
    var tail = head
    val tailPositions = mutableListOf(tail)
    for (motion in motions) {
        for (step in 0 until motion.steps) {
            head = head.move(motion.direction)
            tail = tail.follow(head)
            tailPositions.add(tail)
        }
    }
    return tailPositions
}


data class Coordinate(val x: Int, val y: Int) {
    fun move(direction: Direction): Coordinate =
        when (direction) {
            Direction.LEFT -> Coordinate(x - 1, y)
            Direction.RIGHT -> Coordinate(x + 1, y)
            Direction.UP -> Coordinate(x, y + 1)
            Direction.DOWN -> Coordinate(x, y - 1)
        }

    fun follow(other: Coordinate): Coordinate =
        if (other.x == x) {
            if (other.y > y + 1) {
                move(Direction.UP)
            } else if (other.y < y - 1) {
                move(Direction.DOWN)
            } else {
                this
            }
        } else if (other.y == y) {
            if (other.x > x + 1) {
                move(Direction.RIGHT)
            } else if (other.x < x - 1) {
                move(Direction.LEFT)
            } else {
                this
            }
        } else if (distance(other) > 2) {
            move(
                if (other.x > x) {
                    Direction.RIGHT
                } else {
                    Direction.LEFT
                }
            ).move(
                if (other.y > y) {
                    Direction.UP
                } else {
                    Direction.DOWN
                }
            )
        } else {
            this
        }

    private fun distance(other: Coordinate): Int = abs(x - other.x) + abs(y - other.y)
}

