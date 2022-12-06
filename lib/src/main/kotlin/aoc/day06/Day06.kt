package aoc.day06

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.regexToken

object Day06Grammar : AocGrammar<String>() {
    private val charToken by regexToken("[a-z]+")

    override val rootParser by (charToken use { text }).eolTerminated()
}

fun parse(data: String): String = Day06Grammar.parseToEnd(data)

fun findMarker(packets: String, markerSize: Int): Int {
    return markerSize + packets.windowed(markerSize, 1)
        .indexOfFirst { it.toSet().size == it.length }
}

fun part1(input: String): Int = findMarker(parse(input), 4)
fun part2(input: String): Int = findMarker(parse(input), 14)