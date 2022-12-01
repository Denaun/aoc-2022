package aoc.day01

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.separatedTerms
import com.github.h0tk3y.betterParse.grammar.parseToEnd


object Day01Grammar : AocGrammar<List<List<Int>>>() {
    private val group by separatedTerms(numberParser, eolToken).eolTerminated()

    override val rootParser by separatedTerms(group, eolToken)
}

fun parse(data: String): List<List<Int>> {
    return Day01Grammar.parseToEnd(data)
}

fun largestGroupSum(groups: List<List<Int>>): Int? {
    return groups.maxOfOrNull { group -> group.sum() }
}

fun part1(input: String): Int {
    return largestGroupSum(parse(input))!!
}