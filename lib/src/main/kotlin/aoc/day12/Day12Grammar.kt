package aoc.day12

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.oneOrMore
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.regexToken

object Day12Grammar : AocGrammar<List<List<Char>>>() {
    private val cellToken by regexToken("[a-zSE]")
    private val cellParser by cellToken use { text[0] }

    override val rootParser by lineList(oneOrMore(cellParser))
}

fun parse(data: String): List<List<Char>> = Day12Grammar.parseToEnd(data)