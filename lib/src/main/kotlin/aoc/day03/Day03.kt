package aoc.day03

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.regexToken
import com.google.common.collect.Iterables.getOnlyElement

object Day03Grammar : AocGrammar<List<String>>() {
    private val rucksackToken by regexToken("[a-zA-Z]+")

    private val rucksack by rucksackToken use { text }

    override val rootParser by lineList(rucksack)
}

fun parse(data: String): List<String> = Day03Grammar.parseToEnd(data)

fun Char.priority(): Int {
    require(this in ('a'..'z') + ('A'..'Z'))
    if (this in 'a'..'z') {
        return this.dec() - 'a'.dec() + 1
    }
    if (this in 'A'..'Z') {
        return this.dec() - 'A'.dec() + 27
    }
    throw IllegalStateException()
}

fun misplacedItem(rucksack: String): Char {
    require(rucksack.length % 2 == 0)
    val mid = rucksack.length / 2
    val firstCompartment = rucksack.subSequence(0, mid).toSet()
    val secondCompartment = rucksack.subSequence(mid, rucksack.length).toSet()
    return getOnlyElement(firstCompartment.intersect(secondCompartment))
}

fun badge(group: List<String>): Char {
    require(group.size % 3 == 0)
    return getOnlyElement(group.map(String::toSet).reduce(Set<Char>::intersect))
}

fun part1(input: String): Int = parse(input).sumOf { misplacedItem(it).priority() }

fun part2(input: String): Int = parse(input).chunked(3).sumOf { badge(it).priority() }