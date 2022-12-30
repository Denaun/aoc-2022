package aoc.day12

const val START: Char = 'S'
const val END: Char = 'E'

typealias HeightMap = List<List<Char>>

fun part1(input: String): Int = shortestPath(parse(input))!!

fun shortestPath(heightMap: HeightMap): Int? {
    val toVisit = mutableListOf(findStart(heightMap))
    val visited = mutableSetOf<Coordinate>()
    var steps = 0
    while (toVisit.isNotEmpty()) {
        repeat(toVisit.size) {
            val current = toVisit.removeFirst()
            if (heightMap[current] == END) {
                return steps
            }
            if (!visited.contains(current)) {
                visited.add(current)
                toVisit.addAll(current.visitableNeighbors(heightMap))
            }
        }
        steps += 1
    }
    return null
}


data class Coordinate(val row: Int, val column: Int) {
    private fun neighbors(): List<Coordinate> = listOf(
        Coordinate(row - 1, column),
        Coordinate(row + 1, column),
        Coordinate(row, column - 1),
        Coordinate(row, column + 1),
    )

    fun visitableNeighbors(heightMap: HeightMap): List<Coordinate> = neighbors()
        .filter {
            0 <= it.row && it.row < heightMap.size &&
                    0 <= it.column && it.column < heightMap[it.row].size &&
                    height(heightMap[it]) - height(heightMap[this]) <= 1
        }
}

fun findStart(heightMap: HeightMap): Coordinate {
    val row = heightMap.indexOfFirst { it.contains(START) }
    val column = heightMap[row].indexOf(START)
    return Coordinate(row, column)
}

fun height(c: Char): Int = when (c) {
    START -> height('a')
    END -> height('z')
    else -> c.code
}

operator fun HeightMap.get(c: Coordinate): Char = this[c.row][c.column]