package aoc.day04

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken
import com.google.common.collect.Range
import com.google.common.collect.Range.closed

object Day04Grammar : AocGrammar<List<Pair<Range<Int>, Range<Int>>>>() {
    private val dashToken by literalToken("-")
    private val commaToken by literalToken(",")

    private val rangeParser by separatedPair(numberParser, dashToken, numberParser, ::closed)
    private val rangePairParser by separatedPair(rangeParser, commaToken, rangeParser)

    override val rootParser by lineList(rangePairParser)
}

fun parse(data: String): List<Pair<Range<Int>, Range<Int>>> = Day04Grammar.parseToEnd(data)

fun isFullOverlap(first: Range<Int>, second: Range<Int>): Boolean =
    first.encloses(second) || second.encloses(first)

fun part1(input: String): Int =
    parse(input).count { (first, second) -> isFullOverlap(first, second) }