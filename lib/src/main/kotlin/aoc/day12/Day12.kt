package aoc.day12

const val START: Char = 'S'
const val END: Char = 'E'

typealias HeightMap = List<List<Char>>

fun part1(input: String): Int = shortestPath(parse(input))!!

fun part2(input: String): Int = mostScenicPath(parse(input))!!

fun shortestPath(heightMap: HeightMap): Int? = bfs(
    findFirst(heightMap, START),
    isEnd = { heightMap[it] == END },
    neighbors = { it.uphillNeighbors(heightMap) },
)

fun mostScenicPath(heightMap: HeightMap): Int? = bfs(
    findFirst(heightMap, END),
    isEnd = { heightMap[it] == 'a' },
    neighbors = { it.downhillNeighbors(heightMap) },
)

fun bfs(
    start: Coordinate,
    isEnd: (Coordinate) -> Boolean,
    neighbors: (Coordinate) -> List<Coordinate>,
): Int? {
    val toVisit = mutableListOf(start)
    val visited = mutableSetOf<Coordinate>()
    var steps = 0
    while (toVisit.isNotEmpty()) {
        repeat(toVisit.size) {
            val current = toVisit.removeFirst()
            if (isEnd(current)) {
                return steps
            }
            if (!visited.contains(current)) {
                visited.add(current)
                toVisit.addAll(neighbors(current))
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

    private fun visitableNeighbors(heightMap: HeightMap): List<Coordinate> = neighbors()
        .filter {
            0 <= it.row && it.row < heightMap.size &&
                    0 <= it.column && it.column < heightMap[it.row].size
        }

    fun uphillNeighbors(heightMap: HeightMap): List<Coordinate> =
        visitableNeighbors(heightMap)
            .filter { height(heightMap[it]) - height(heightMap[this]) <= 1 }

    fun downhillNeighbors(heightMap: HeightMap): List<Coordinate> =
        visitableNeighbors(heightMap)
            .filter { height(heightMap[this]) - height(heightMap[it]) <= 1 }
}

fun findFirst(heightMap: HeightMap, cell: Char): Coordinate {
    val row = heightMap.indexOfFirst { it.contains(cell) }
    val column = heightMap[row].indexOf(cell)
    return Coordinate(row, column)
}

fun height(c: Char): Int = when (c) {
    START -> height('a')
    END -> height('z')
    else -> c.code
}

operator fun HeightMap.get(c: Coordinate): Char = this[c.row][c.column]