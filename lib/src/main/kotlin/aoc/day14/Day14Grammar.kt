package aoc.day14

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.map
import com.github.h0tk3y.betterParse.combinators.separatedTerms
import com.github.h0tk3y.betterParse.combinators.times
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken


object Day14Grammar : AocGrammar<List<Path>>() {
    private val arrowToken by literalToken("-> ")
    private val commaToken by literalToken(",")

    private val pointParser by separatedPair(numberParser, commaToken, numberParser, ::Point)
    private val pathParser by separatedTerms(pointParser, spaceToken * arrowToken).map(::Path)

    override val rootParser by lineList(pathParser)
}

fun parse(data: String): List<Path> = Day14Grammar.parseToEnd(data)