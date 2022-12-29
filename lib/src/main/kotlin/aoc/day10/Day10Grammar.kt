package aoc.day10

import aoc.AocGrammar
import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.literalToken

object Day10Grammar : AocGrammar<List<Instruction>>() {
    private val noopToken by literalToken("noop")
    private val addXToken by literalToken("addx")

    private val minusToken by literalToken("-")
    private val signedParser by (-minusToken and numberParser) map { -it } or numberParser

    private val noopParser by noopToken asJust Noop
    private val addXParser by -(addXToken and spaceToken) and signedParser map { AddX(it) }

    override val rootParser by lineList(noopParser or addXParser)
}

fun parse(data: String): List<Instruction> = Day10Grammar.parseToEnd(data)