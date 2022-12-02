package aoc.day02

enum class Result(val score: Int) { LOSS(0), DRAW(3), WIN(6) }

enum class Shape(val score: Int) {
    ROCK(1) {
        override fun defeats(other: Shape) = other == SCISSORS
    },
    PAPER(2) {
        override fun defeats(other: Shape) = other == ROCK
    },
    SCISSORS(3) {
        override fun defeats(other: Shape) = other == PAPER
    };

    abstract fun defeats(other: Shape): Boolean

    infix fun versus(other: Shape): Result {
        return if (this.defeats(other)) {
            Result.WIN
        } else if (other.defeats(this)) {
            Result.LOSS
        } else {
            check(this == other)
            Result.DRAW
        }
    }
}

fun score(lhs: Shape, rhs: Shape): Int = rhs.score + (rhs versus lhs).score

fun part1(input: String): Int = parse(input).sumOf { (lhs, rhs) -> score(lhs, rhs) }
