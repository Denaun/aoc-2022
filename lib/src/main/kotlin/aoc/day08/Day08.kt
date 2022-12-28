package aoc.day08

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import kotlin.math.max

fun part1(input: String): Int = countAllVisible(parse(input))

fun part2(input: String): Int = bestScenicScore(parse(input))

object Day08Grammar : AocGrammar<List<List<Int>>>() {
    override val rootParser by lineList(numberToken use { text.map { it.digitToInt() } })
}

fun parse(data: String): List<List<Int>> = Day08Grammar.parseToEnd(data)

fun countAllVisible(trees: List<List<Int>>): Int {
    val rows = trees.size
    val columns = trees[0].size
    val visibleDirections = List(rows) { MutableList(columns) { false } }
    for ((i, row) in trees.withIndex()) {
        for ((j, isVisible) in visibleTrees(row).withIndex()) {
            visibleDirections[i][j] = visibleDirections[i][j] || isVisible
        }
        for ((j, isVisible) in visibleTrees(row.reversed()).reversed().withIndex()) {
            visibleDirections[i][j] = visibleDirections[i][j] || isVisible
        }
    }
    for (j in 0 until columns) {
        val column = trees.map { it[j] }
        for ((i, isVisible) in visibleTrees(column).withIndex()) {
            visibleDirections[i][j] = visibleDirections[i][j] || isVisible
        }
        for ((i, isVisible) in visibleTrees(column.reversed()).reversed().withIndex()) {
            visibleDirections[i][j] = visibleDirections[i][j] || isVisible
        }
    }
    return visibleDirections.sumOf { row -> row.count { it } }
}

fun visibleTrees(trees: Iterable<Int>): List<Boolean> =
    trees.runningFold(Int.MIN_VALUE, ::max).windowed(2, 1)
        .map { (first, second) -> first != second }

fun bestScenicScore(trees: List<List<Int>>): Int = trees.indices.maxOf { row ->
    trees[row].indices.maxOf { column -> scenicScore(row, column, trees) }
}

fun scenicScore(row: Int, column: Int, trees: List<List<Int>>): Int {
    val rows = trees.size
    val columns = trees[row].size
    val height = trees[row][column]
    val leftEdge = (0 until column).lastOrNull { trees[row][it] >= height } ?: 0
    val rightEdge =
        (column + 1 until columns).firstOrNull { trees[row][it] >= height } ?: (columns - 1)
    val upEdge = (0 until row).lastOrNull { trees[it][column] >= height } ?: 0
    val downEdge = (row + 1 until rows).firstOrNull { trees[it][column] >= height } ?: (rows - 1)
    val left = column - leftEdge
    val right = rightEdge - column
    val up = row - upEdge
    val down = downEdge - row
    return left * right * up * down
}