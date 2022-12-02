package aoc.day02

enum class Result(val score: Int) { LOSS(0), DRAW(3), WIN(6) }

enum class Shape(val score: Int) {
    ROCK(1) {
        override fun shapeForResult(result: Result) = when (result) {
            Result.WIN -> PAPER
            Result.DRAW -> this
            Result.LOSS -> SCISSORS
        }
    },
    PAPER(2) {
        override fun shapeForResult(result: Result) = when (result) {
            Result.WIN -> SCISSORS
            Result.DRAW -> this
            Result.LOSS -> ROCK
        }
    },
    SCISSORS(3) {
        override fun shapeForResult(result: Result) = when (result) {
            Result.WIN -> ROCK
            Result.DRAW -> this
            Result.LOSS -> PAPER
        }
    };

    /** Which [Shape] needs to be played against this to achieve [result]. */
    abstract fun shapeForResult(result: Result): Shape

    infix fun versus(other: Shape): Result {
        return Result.values().find { this == other.shapeForResult(it) }!!
    }
}

fun score(lhs: Shape, rhs: Shape): Int = rhs.score + (rhs versus lhs).score
fun score(lhs: Shape, result: Result): Int = lhs.shapeForResult(result).score + result.score

fun part1(input: String): Int = parse(input).sumOf { (lhs, rhs) -> score(lhs, toShape(rhs)) }

fun part2(input: String): Int = parse(input).sumOf { (lhs, rhs) -> score(lhs, toResult(rhs)) }

fun toShape(unknown: Unknown): Shape = when (unknown) {
    Unknown.X -> Shape.ROCK
    Unknown.Y -> Shape.PAPER
    Unknown.Z -> Shape.SCISSORS
}

fun toResult(unknown: Unknown): Result = when (unknown) {
    Unknown.X -> Result.LOSS
    Unknown.Y -> Result.DRAW
    Unknown.Z -> Result.WIN
}