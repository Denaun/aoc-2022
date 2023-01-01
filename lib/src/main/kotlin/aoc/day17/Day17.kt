package aoc.day17

fun part1(input: String): Int {
    val tetris = Tetris(7, parse(input))
    tetris.simulate(2022)
    return tetris.towerHeight()
}

data class Coordinate(val row: Int, val column: Int) {
    infix operator fun plus(other: Coordinate): Coordinate =
        Coordinate(row + other.row, column + other.column)

    fun move(direction: Direction) = when (direction) {
        Direction.LEFT -> Coordinate(row, column - 1)
        Direction.RIGHT -> Coordinate(row, column + 1)
    }

    fun moveDown() = Coordinate(row - 1, column)
}

class Tetris(private val width: Int, directions: List<Direction>) {
    private val directions: Iterator<Direction> = loop(directions)
    private val rocks: Iterator<Rock> = loop(allRocks())
    private val cave: MutableList<MutableList<Boolean>> = mutableListOf()

    fun towerHeight(): Int = 1 + cave.indexOfLast { it.any() }

    fun simulate(numRocks: Int) {
        repeat(numRocks) {
            val rock = rocks.next()
            val bottomLeft = simulateRock(rock)
            val rockIndices = rock.indices.map(bottomLeft::plus)
            reserve(rockIndices.maxOf { it.row })
            rockIndices.forEach {
                cave[it.row][it.column] = true
            }
        }
    }

    private fun simulateRock(rock: Rock): Coordinate {
        var bottomLeft = Coordinate(towerHeight() + 3, 2)
        while (true) {
            tryPushing(bottomLeft, directions.next(), rock)?.also { bottomLeft = it }
            tryFalling(bottomLeft, rock)?.also { bottomLeft = it } ?: return bottomLeft
        }
    }

    private fun reserve(height: Int) {
        if (cave.size > height) {
            return
        }
        cave.addAll(List(height - cave.size + 1) { MutableList(width) { false } })
    }

    private fun tryFalling(bottomLeft: Coordinate, rock: Rock): Coordinate? =
        bottomLeft.moveDown().takeUnless { collides(rock, it) }

    private fun tryPushing(bottomLeft: Coordinate, direction: Direction, rock: Rock): Coordinate? =
        bottomLeft.move(direction).takeUnless { collides(rock, it) }

    private fun Rock.offseted(bottomLeft: Coordinate): List<Coordinate> =
        indices.map(bottomLeft::plus)

    private fun collides(rock: Rock, bottomLeft: Coordinate): Boolean =
        rock.offseted(bottomLeft).any {
            it.column !in 0 until width || it.row < 0 || (it.row < cave.size && cave[it.row][it.column])
        }
}

fun <T> loop(values: Iterable<T>): Iterator<T> =
    generateSequence { values.asSequence() }.flatten().iterator()