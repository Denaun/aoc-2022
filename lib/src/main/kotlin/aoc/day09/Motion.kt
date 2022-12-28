package aoc.day09

enum class Direction {
    LEFT, RIGHT, UP, DOWN
}

data class Motion(val direction: Direction, val steps: Int)
