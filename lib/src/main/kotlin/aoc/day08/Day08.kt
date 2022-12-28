package aoc.day08

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import kotlin.math.max

fun part1(input: String): Int = countAllVisible(parse(input))

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
